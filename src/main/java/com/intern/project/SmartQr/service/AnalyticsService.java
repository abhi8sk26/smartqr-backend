package com.intern.project.SmartQr.service;

import com.intern.project.SmartQr.dto.ScanStatsDTO;
import com.intern.project.SmartQr.model.App;
import com.intern.project.SmartQr.model.Scan;
import com.intern.project.SmartQr.repository.AppRepository;
import com.intern.project.SmartQr.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    @Autowired
    private ScanRepository scanRepository;
    @Autowired
    private AppRepository appRepository;

    public List<ScanStatsDTO> getDailyScans(Long appId, LocalDate start, LocalDate end) {
        App app = appRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("App not found"));

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.plusDays(1).atStartOfDay(); // inclusive end

        List<Scan> scans = scanRepository.findByAppAndScanTimeBetween(app, startDateTime, endDateTime);

        // Group by date and device
        Map<LocalDate, Map<String, Long>> grouped = scans.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getScanTime().toLocalDate(),
                        Collectors.groupingBy(Scan::getDeviceType, Collectors.counting())
                ));

        List<ScanStatsDTO> result = new ArrayList<>();
        grouped.forEach((date, deviceMap) ->
                deviceMap.forEach((device, count) ->
                        result.add(new ScanStatsDTO(date, count, device))
                )
        );
        return result;
    }
}