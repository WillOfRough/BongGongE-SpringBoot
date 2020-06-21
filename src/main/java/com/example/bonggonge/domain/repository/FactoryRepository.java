package com.example.bonggonge.domain.repository;

import com.example.bonggonge.domain.entity.FactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactoryRepository extends JpaRepository<FactoryEntity, Long> {
    @Modifying
    @Query(value = "SELECT id, ( 6371 * acos( cos( radians(37) ) * cos( radians( ?2 ) ) " +
            "* cos( radians( ?3 ) - radians(127) ) + sin( radians(37) ) * sin( radians( ?2 ) ) ) ) " +
            "AS distance FROM TEST_TABLE HAVING distance < ?1", nativeQuery = true)
    List<FactoryEntity> listFactory(double distance, double latitude, double longitude);
}
