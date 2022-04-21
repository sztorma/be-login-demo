package com.sztorma.logindemo.security.facade;

import java.util.Date;
import java.util.HashMap;

import com.sztorma.logindemo.security.JwtTokenUtil;
import com.sztorma.logindemo.security.model.CaptchaRequest;
import com.sztorma.logindemo.security.model.JwtRequest;
import com.sztorma.logindemo.security.model.JwtResponse;
import com.sztorma.logindemo.user.entity.User;
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
    private JwtTokenUtil captchaTokenUtil;

    @Autowired
    private UserService userService;

    public JwtResponse createAuthenticationResponse(JwtRequest authenticationRequest) throws Exception {
        final Authentication auth = authenticate(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        String token = null;
        String captchaToken = null;
        User user = userService.getUserByName(authenticationRequest.getUsername());
        boolean captchaRequired = userService.getCaptchaRequired(user);
        if (!captchaRequired) {
            token = jwtTokenUtil.generateToken(auth);
            userService.saveLastLogin(user, new Date());
        } else {
            captchaToken = captchaTokenUtil.generateToken(auth);
        }
        return new JwtResponse(token, captchaToken);
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
    public void saveLastLogin(User user, Date date) {
        userService.saveLastLogin(user, new Date());

    }

    @Transactional
    public void increaseLoginAttempt(String username) {
        userService.increaseLoginAttempt(username);
    }

    @Transactional
    public JwtResponse captchaAuthentication(CaptchaRequest captchaRequest) {
        final String username = captchaTokenUtil.getUsernameFromToken(captchaRequest.getCaptchaToken());
        final User user = userService.getUserByName(username);
        String jwt = jwtTokenUtil.doGenerateToken(new HashMap<>(), user.getUsername(), System.currentTimeMillis(),
                5 * 60 * 60);
        userService.saveLastLogin(user, new Date());
        userService.resetLoginAttempt(user);
        return new JwtResponse(jwt, null);
    }
}
