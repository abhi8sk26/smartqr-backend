package com.intern.project.SmartQr.repository;

import com.intern.project.SmartQr.model.App;
import com.intern.project.SmartQr.model.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ScanRepository extends JpaRepository<Scan, Long> {
    long countByApp(App app);

    List<Scan> findByAppAndScanTimeBetween(App app, LocalDateTime start, LocalDateTime end);

    // Optional: custom query for daily grouped stats
    @Query("SELECT FUNCTION('DATE', s.scanTime), s.deviceType, COUNT(s) " +
            "FROM Scan s WHERE s.app = :app AND s.scanTime BETWEEN :start AND :end " +
            "GROUP BY FUNCTION('DATE', s.scanTime), s.deviceType")
    List<Object[]> getDailyStats(@Param("app") App app, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}