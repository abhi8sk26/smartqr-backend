package com.intern.project.SmartQr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "scans")
@Data
@NoArgsConstructor
public class Scan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    private LocalDateTime scanTime;
    private String deviceType;   // "iOS", "Android", "other"
}