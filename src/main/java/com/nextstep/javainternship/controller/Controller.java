package com.nextstep.javainternship.controller;

import com.nextstep.javainternship.entity.User;
import com.nextstep.javainternship.repository.UserRepository;
import com.nextstep.javainternship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Validated
@RestController
public class Controller {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    UserService userService;

    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewAdmin(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("Successfully saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getUserDetails/{id}")
    public ResponseEntity<User> getAdminDetails(@PathVariable("id") int id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUserInfo/{id}")
    public ResponseEntity<String> updateAdminInfo(@Valid @RequestBody User user, @PathVariable("id") int id) {
        try {
            userService.updateUser(user, id);
            return ResponseEntity.ok("Successfully Updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            User user = userService.getUserNameAndPassword(username, password);
            if (user != null) {
                // Return success response
                return ResponseEntity.ok("Successfully logged in!");
            } else {
                // Return error response
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

