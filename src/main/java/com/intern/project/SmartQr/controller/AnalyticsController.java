package com.intern.project.SmartQr.controller;

import com.intern.project.SmartQr.dto.ScanStatsDTO;
import com.intern.project.SmartQr.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/{appId}/daily")
    public List<ScanStatsDTO> getDailyScans(@PathVariable Long appId,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return analyticsService.getDailyScans(appId, start, end);
    }
}