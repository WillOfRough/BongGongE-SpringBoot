package com.example.bonggonge.domain.repository;


import com.example.bonggonge.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findById(Long userNo);

//    @Transactional
//    @Modifying
//    @Query(value ="update user set money = money + :money where no = :no",nativeQuery = true)
//    Integer updateMoney(@Param("no") Long no, @Param("money") int money);
}
