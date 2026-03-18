package com.intern.project.SmartQr.service;

import com.intern.project.SmartQr.utils.QRGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class QRService {
    @Autowired
    private QRGenerator qrGenerator;

    public String generateQRForUrl(String url) throws IOException {
        try {
            return qrGenerator.generateQRCodeBase64(url, 300, 300);
        } catch (Exception e) {
            throw new IOException("Failed to generate QR code", e);
        }
    }
}