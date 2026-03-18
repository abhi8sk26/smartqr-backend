package com.intern.project.SmartQr.service;

import com.intern.project.SmartQr.dto.AppRequest;
import com.intern.project.SmartQr.dto.AppResponse;
import com.intern.project.SmartQr.model.App;
import com.intern.project.SmartQr.model.User;
import com.intern.project.SmartQr.repository.AppRepository;
import com.intern.project.SmartQr.repository.ScanRepository;
import com.intern.project.SmartQr.utils.QRGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private ScanRepository scanRepository;
    @Autowired
    private QRGenerator qrGenerator;

    @Value("${app.base-url}")
    private String baseUrl;

    public AppResponse createApp(AppRequest request, User user) throws Exception {
        App app = new App();
        app.setName(request.getName());
        app.setPlayStoreLink(request.getPlayStoreLink());
        app.setAppStoreLink(request.getAppStoreLink());
        app.setUser(user);

        // Generate QR code pointing to our redirect endpoint
        String redirectUrl = baseUrl + "/r/" + app.getId(); // ID will be generated after save? Need ID first.
        // Since ID is generated on save, we need two-step process: save first without QR, then update.
        // Alternative: generate QR with a placeholder, then update after save. Let's do two-step.
        App savedApp = appRepository.save(app); // now has ID
        String qrUrl = baseUrl + "/r/" + savedApp.getId();
        String qrBase64 = qrGenerator.generateQRCodeBase64(qrUrl, 300, 300);
        savedApp.setQrCodeBase64(qrBase64);
        appRepository.save(savedApp); // update with QR

        return toResponse(savedApp);
    }

    public List<AppResponse> getUserApps(User user) {
        return appRepository.findByUser(user).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AppResponse toResponse(App app) {
        AppResponse res = new AppResponse();
        res.setId(app.getId());
        res.setName(app.getName());
        res.setPlayStoreLink(app.getPlayStoreLink());
        res.setAppStoreLink(app.getAppStoreLink());
        res.setQrCodeBase64(app.getQrCodeBase64());
        res.setScanCount(scanRepository.countByApp(app));
        return res;
    }
}