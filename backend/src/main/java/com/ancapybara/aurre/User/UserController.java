package com.ancapybara.aurre.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user/get")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String username = principal.getName();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>
                (new UserInfoResponse(user.get().getId(), username, user.get().getAvatarFilename()), HttpStatus.OK);
    }

    @GetMapping("/user/get/{username}")
    public ResponseEntity<?> getUserInfoByUsername(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        UserInfoResponse response = new UserInfoResponse(user.get().getId(), username, user.get().getAvatarFilename());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
