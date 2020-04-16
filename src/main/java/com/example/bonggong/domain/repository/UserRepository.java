package com.example.bonggong.domain.repository;

import com.example.bonggong.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(Long userNo);

    @Transactional
    @Modifying
    @Query(value ="update user set money = money + :money where no = :no",nativeQuery = true)
    Integer updateMoney(@Param("no") Long no, @Param("money") int money);
}
