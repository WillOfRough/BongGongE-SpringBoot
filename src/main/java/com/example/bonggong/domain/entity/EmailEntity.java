package com.example.bonggong.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Document(collection = "Email")
@Getter
@Setter
public class EmailEntity {

    @Id
    @Column(length = 30, nullable = false)
    private String email;

    @Column(name = "check_num")
    private int checkNum;

    @ColumnDefault("false")
    private boolean certified;

    @Builder
    public EmailEntity(String email, int checkNum, boolean certified) {
        this.email=email;
        this.checkNum=checkNum;
        this.certified = certified;
    }
}