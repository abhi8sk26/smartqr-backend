package com.intern.project.SmartQr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ScanStatsDTO {
    private LocalDate date;
    private long count;
    private String deviceType;
}