package com.intern.project.SmartQr.service;

import com.intern.project.SmartQr.model.App;
import com.intern.project.SmartQr.model.Scan;
import com.intern.project.SmartQr.repository.AppRepository;
import com.intern.project.SmartQr.repository.ScanRepository;
import com.intern.project.SmartQr.utils.DeviceDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class RedirectService {
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ScanRepository scanRepository;
    @Autowired
    private DeviceDetector deviceDetector;

    @Transactional
    public String getRedirectUrl(Long appId, String userAgent) {
        App app = appRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("App not found"));

        // Record scan
        Scan scan = new Scan();
        scan.setApp(app);
        scan.setScanTime(LocalDateTime.now());
        scan.setDeviceType(deviceDetector.detectDevice(userAgent));
        scanRepository.save(scan);

        // Return appropriate store link
        String device = scan.getDeviceType();
        if ("iOS".equals(device)) {
            return app.getAppStoreLink();
        } else if ("Android".equals(device)) {
            return app.getPlayStoreLink();
        } else {
            // Fallback: maybe return a web page or just Play Store
            return app.getPlayStoreLink();
        }
    }
}