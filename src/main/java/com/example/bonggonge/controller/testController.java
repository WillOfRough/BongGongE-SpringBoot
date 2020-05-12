package com.example.bonggonge.controller;


import com.example.bonggonge.domain.entity.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/template")
public class testController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MongoTemplate mongoTemplate;

    public testController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<EmployeeEntity> getAllUsers() {
        logger.info("Getting all Employees.");
        return mongoTemplate.findAll(EmployeeEntity.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeEntity getEmployee(@PathVariable long id) {
        logger.info("Getting Employee with ID: {}.", id);
        EmployeeEntity employeeModel = mongoTemplate.findById(id, EmployeeEntity.class);
        return employeeModel;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public EmployeeEntity add(@RequestBody EmployeeEntity employeeModel) {
        logger.info("Saving Employee.");
        return mongoTemplate.save(employeeModel);
    }
}