package com.ancapybara.aurre.security.service;

import com.ancapybara.aurre.domain.dto.auth.AuthResponseDTO;
import com.ancapybara.aurre.domain.dto.auth.AuthRequestDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
    AuthResponseDTO register(AuthRequestDTO dto);
}
