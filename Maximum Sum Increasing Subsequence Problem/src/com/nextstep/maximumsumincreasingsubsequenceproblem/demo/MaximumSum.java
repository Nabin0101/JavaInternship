package com.nextstep.maximumsumincreasingsubsequenceproblem.demo;

public class MaximumSum {

    public static int maxSumIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxSum = 0;

        // Initialize dp array with the values of the input array
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }

        // Compute maximum sum increasing subsequence
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
        }

        // Find the maximum sum
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11};
        System.out.println("Maximum sum increasing subsequence: " + maxSumIncreasingSubsequence(arr));
    }
}
