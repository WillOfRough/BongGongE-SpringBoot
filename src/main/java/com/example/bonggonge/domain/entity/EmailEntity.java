package com.example.bonggonge.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Email")
@Getter
@Setter
public class EmailEntity {

    @Id
    private String email;
    private int checkNum;
    private boolean certified;

    @Builder
    public EmailEntity(String email, int checkNum, boolean certified) {
        this.email=email;
        this.checkNum=checkNum;
        this.certified = certified;
    }
}