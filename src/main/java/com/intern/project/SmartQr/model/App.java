package com.intern.project.SmartQr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apps")
@Data
@NoArgsConstructor
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String playStoreLink;
    private String appStoreLink;

    @Column(length = 5000)
    private String qrCodeBase64;   // Base64 encoded PNG image

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}