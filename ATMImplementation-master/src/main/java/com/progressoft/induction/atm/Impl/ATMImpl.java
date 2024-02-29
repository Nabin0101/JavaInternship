package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem=new BankingSystemImpl();
    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        BigDecimal accountBalance = bankingSystem.getAccountBalance(accountNumber);
        List<Banknote> withdrawnBanknotes = new ArrayList<>();

        // Check if account has sufficient funds
        if (accountBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account");
        }

        // Check if ATM has enough money
        BigDecimal remainingAmount = amount;
        EnumMap<Banknote, Integer> atmCashMap = bankingSystem.getAtmCashMap();
        for (Banknote banknote : Banknote.values()) {
            BigDecimal banknoteValue = banknote.getValue();
            int banknoteCount = atmCashMap.getOrDefault(banknote, 0);

            // Calculate how many banknotes of this denomination can be withdrawn
            int banknotesToWithdraw = remainingAmount.divideToIntegralValue(banknoteValue).intValue();

            // Limit withdrawal to the number of banknotes available in the ATM
            banknotesToWithdraw = Math.min(banknotesToWithdraw, banknoteCount);

            // Withdraw the banknotes
            for (int i = 0; i < banknotesToWithdraw; i++) {
                withdrawnBanknotes.add(banknote);
                remainingAmount = remainingAmount.subtract(banknoteValue);
                banknoteCount--;
            }

            atmCashMap.put(banknote, banknoteCount);

            // Check if remaining amount is zero, break if so
            if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }

        // Check if ATM has enough money
        if (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            throw new NotEnoughMoneyInATMException("Not enough money in ATM to complete withdrawal");
        }

        // Debit account
        bankingSystem.debitAccount(accountNumber, amount);

        return withdrawnBanknotes;
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        return bankingSystem.getAccountBalance(accountNumber);
    }
}
