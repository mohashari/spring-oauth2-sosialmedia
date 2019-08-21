package com.kodascript.oauth2sosial.controller;

import com.kodascript.oauth2sosial.exception.ResourceNotFoundException;
import com.kodascript.oauth2sosial.model.User;
import com.kodascript.oauth2sosial.repository.UserRepository;
import com.kodascript.oauth2sosial.security.CurrenUser;
import com.kodascript.oauth2sosial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrenUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userPrincipal.getId()));
    }
}
