package com.ancapybara.aurre.security.service.impl;

import com.ancapybara.aurre.domain.dto.auth.AuthResponseDTO;
import com.ancapybara.aurre.domain.entity.Role;
import com.ancapybara.aurre.domain.entity.User;
import com.ancapybara.aurre.service.impl.UserServiceDefault;
import com.ancapybara.aurre.domain.dto.auth.AuthRequestDTO;
import com.ancapybara.aurre.repository.RoleRepository;
import com.ancapybara.aurre.repository.UserRepository;
import com.ancapybara.aurre.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@Primary
public class AuthServiceDefault implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserServiceDefault userServiceDefault;

    @Autowired
    public AuthServiceDefault(AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder,
                              UserDetailsService userDetailsService,
                              TokenService tokenService,
                              UserRepository userRepository,
                              RoleRepository roleRepository,
                              UserServiceDefault userServiceDefault) {

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userServiceDefault = userServiceDefault;
    }


    public AuthResponseDTO login(AuthRequestDTO req) throws BadCredentialsException {
        String username = req.getUsername();
        String password = req.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = (User) userDetailsService.loadUserByUsername(username);
        String access_token = tokenService.generateAccessToken(user);
        return new AuthResponseDTO("Success", access_token);
    }

    public AuthResponseDTO register(AuthRequestDTO req) {
        String username = req.getUsername();
        String password = req.getPassword();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Role role = new Role("user");
        Set<Role> roles = Collections.singleton(role);
        User user = new User(username, passwordEncoder.encode(password), roles);
        String accessToken = tokenService.generateAccessToken(user);
        roleRepository.save(role);
        userServiceDefault.saveUser(user);
        return new AuthResponseDTO("Success", accessToken);
    }
}
