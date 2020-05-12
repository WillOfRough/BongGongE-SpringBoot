package com.example.bonggonge.domain.repository;

import com.example.bonggonge.domain.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, Long > {

}