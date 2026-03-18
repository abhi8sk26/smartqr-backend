package com.intern.project.SmartQr.repository;

import com.intern.project.SmartQr.model.App;
import com.intern.project.SmartQr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppRepository extends JpaRepository<App, Long> {
    List<App> findByUser(User user);
}