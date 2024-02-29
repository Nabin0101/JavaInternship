package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.User;
import com.nextstep.javainternship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        Optional<User> UserFound=userRepository.findById(id);

        return UserFound.orElse(null);
    }

    @Override
    public void updateUser(User user, int id) {
        Optional<User> userDetails = userRepository.findById(id);
        if (userDetails.isPresent()) {
            User userUpdate = userDetails.get();
            userUpdate.setFullName(user.getFullName());
            userUpdate.setAddress(user.getAddress());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setRole(user.getRole());
            userRepository.save(userUpdate);
        }
    }


    @Override
    public void deleteUser(int id) {
    userRepository.deleteById(id);
    }

    @Override
    public User getUserNameAndPassword(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
