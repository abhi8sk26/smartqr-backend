package com.intern.project.SmartQr.controller;

import com.intern.project.SmartQr.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @GetMapping("/r/{appId}")
    public ResponseEntity<Void> redirect(@PathVariable Long appId,
                                         @RequestHeader(value = "User-Agent", required = false) String userAgent) {
        String url = redirectService.getRedirectUrl(appId, userAgent);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }
}