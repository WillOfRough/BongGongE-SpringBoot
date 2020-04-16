package com.example.bonggong.domain.repository;

import com.example.bonggong.domain.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    List<EmailEntity> findByEmail(String email);

    @Modifying
    @Query(value= "UPDATE email SET certified = true where email = ?1",nativeQuery = true)
    void updateByEmail(String email);
}
