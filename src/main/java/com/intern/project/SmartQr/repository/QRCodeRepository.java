//package com.intern.project.SmartQr.repository;

//import com.intern.project.SmartQr.model.QRCode;
//import com.intern.project.SmartQr.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;

//public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
//    Optional<QRCode> findByCode(String code);
//    Optional<QRCode> findByAppIdAndAppUser(Long appId, User user);
//}