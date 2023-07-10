package com.ancapybara.aurre.Auth;

import com.ancapybara.aurre.Role.Role;
import com.ancapybara.aurre.Role.RoleCrudRepository;
import com.ancapybara.aurre.Security.TokenService;
import com.ancapybara.aurre.User.MyUserDetailsService;
import com.ancapybara.aurre.User.User;
import com.ancapybara.aurre.User.UserRepository;
import com.ancapybara.aurre.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleCrudRepository roleCrudRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthResponse("Wrong password or username", null, null), HttpStatus.UNAUTHORIZED);
        }
        User user = (User) myUserDetailsService.loadUserByUsername(username);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);
        return new ResponseEntity<>(new AuthResponse("Success", access_token, refresh_token), HttpStatus.OK);
    }

    @PostMapping("auth/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        if (userRepository.findByUsername(username).isPresent()) {
            return new ResponseEntity<>(
                    new AuthResponse("User exists", null, null), HttpStatus.UNAUTHORIZED
            );
        }
        Role role = new Role("user");
        Set<Role> roles = Collections.singleton(role);
        User user = new User(username, bCryptPasswordEncoder.encode(password), roles);
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        roleCrudRepository.save(role);
        userService.saveUser(user);
        return new ResponseEntity<>(new AuthResponse("Success", accessToken, refreshToken), HttpStatus.OK);
    }


}
