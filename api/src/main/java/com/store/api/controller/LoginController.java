package com.store.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.store.api.repository.models.JWTRequest;
import com.store.api.repository.models.JWTResponse;
import com.store.api.service.JWTService;
import com.store.api.service.impl.JWTUserDetailService;


@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUserDetailService jwtUserDetailService;
    private final JWTService jwtService;

    public LoginController(AuthenticationManager authenticationManager, JWTUserDetailService jwtUserDetailService, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request) {
        this.authenticate(request);

        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final var token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled");
        }
    }
}