package com.intern.project.SmartQr.controller;

import com.intern.project.SmartQr.dto.AppRequest;
import com.intern.project.SmartQr.dto.AppResponse;
import com.intern.project.SmartQr.model.User;
import com.intern.project.SmartQr.repository.UserRepository;
import com.intern.project.SmartQr.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apps")
public class AppController {

    @Autowired
    private AppService appService;
    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public ResponseEntity<?> createApp(@RequestBody AppRequest request) {
        try {
            User user = getCurrentUser();
            AppResponse response = appService.createApp(request, user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<AppResponse> getUserApps() {
        User user = getCurrentUser();
        return appService.getUserApps(user);
    }
}