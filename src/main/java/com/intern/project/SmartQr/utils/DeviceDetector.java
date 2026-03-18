package com.intern.project.SmartQr.utils;

import org.springframework.stereotype.Component;

@Component
public class DeviceDetector {
    public String detectDevice(String userAgent) {
        if (userAgent == null) return "unknown";
        String ua = userAgent.toLowerCase();
        if (ua.contains("android")) return "Android";
        if (ua.contains("iphone") || ua.contains("ipad") || ua.contains("ipod")) return "iOS";
        return "other";
    }
}