package com.intern.project.SmartQr.dto;

import lombok.Data;

@Data
public class AppResponse {
    private Long id;
    private String name;
    private String playStoreLink;
    private String appStoreLink;
    private String qrCodeBase64;
    private long scanCount;
}