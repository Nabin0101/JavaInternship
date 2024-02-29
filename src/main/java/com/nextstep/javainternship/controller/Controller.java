package com.nextstep.javainternship.controller;

import com.nextstep.javainternship.entity.User;
import com.nextstep.javainternship.repository.UserRepository;
import com.nextstep.javainternship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("Successfully saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getUserDetails/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable("id") int id) {
        try {
            User user = userService.findUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/updateUserInfo/{id}")
    public ResponseEntity<String> updateUserInfo(@Valid @RequestBody User user, @PathVariable("id") int id) {
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
                return ResponseEntity.ok("Successfully logged in!");
            } else {

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

