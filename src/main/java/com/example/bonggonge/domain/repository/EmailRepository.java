package com.example.bonggonge.domain.repository;


import com.example.bonggonge.domain.entity.EmailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailRepository extends MongoRepository<EmailEntity, Long> {
    List<EmailEntity> findByEmail(String email);
//
//    @Modifying
//    @Query(value= "UPDATE email SET certified = true where email = ?1",nativeQuery = true)
//    void updateByEmail(String email);
}
