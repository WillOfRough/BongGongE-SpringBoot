package com.example.bonggong.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "email")
public class EmailEntity {

    @Id
    @Column(length = 30, nullable = false)
    private String email;

    @Column(name = "check_num")
    private int checkNum;

    @ColumnDefault("false") //default 0
    private boolean certified;

    @Builder
    public EmailEntity(String email, int checkNum, boolean certified) {
        this.email=email;
        this.checkNum=checkNum;
        this.certified = certified;
    }
}