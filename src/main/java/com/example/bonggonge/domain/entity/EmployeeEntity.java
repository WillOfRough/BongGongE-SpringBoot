package com.example.bonggonge.domain.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Employee")
@Getter
@Setter
public class EmployeeEntity {
    @Id
    private long id;
    private String name;
    private String address;
    private Date creationDate = new Date();
}
