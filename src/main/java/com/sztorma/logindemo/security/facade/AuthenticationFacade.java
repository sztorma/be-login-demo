package com.sztorma.logindemo.security.facade;

import java.util.Date;

import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.security.model.JwtRequest;
import com.sztorma.logindemo.security.model.JwtResponse;
import com.sztorma.logindemo.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuthenticationFacade {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    public JwtResponse createAuthenticationResponse(JwtRequest authenticationRequest) throws Exception {
        final Authentication auth = authenticate(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        saveLastLogin(authenticationRequest.getUsername(), new Date());
        final String token = jwtTokenUtil.generateToken(auth);
        boolean captchaRequired = getCaptchaRequired(authenticationRequest.getUsername());
        return new JwtResponse(token, captchaRequired);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            increaseLoginAttempt(username);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Transactional
    public void saveLastLogin(String username, Date date) {
        userService.saveLastLogin(username, new Date());
    }

    @Transactional
    public void increaseLoginAttempt(String username) {
        userService.increaseLoginAttempt(username);
    }

    @Transactional(readOnly = true)
    private boolean getCaptchaRequired(String username) {
        return userService.getCaptchaRequired(username);
    }
}
