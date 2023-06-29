package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dto.UserDto;
import io.jsonwebtoken.Claims;

public interface JWTService {
    String generateToken(UserDto userDto);
    Claims decryptJwtToken(String token);
    boolean hasTokenExpired(String token);
    boolean validateAccessToken(String token);
}
