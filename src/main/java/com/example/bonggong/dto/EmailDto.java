package com.example.bonggong.dto;

import com.example.bonggong.domain.entity.EmailEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailDto {
    private String email;
    private int checkNum;
    private boolean certified;

    public EmailEntity toEntity(){
        return EmailEntity.builder()
                .email(email)
                .checkNum(checkNum)
                .certified(certified)
                .build();
    }
    @Builder
    public EmailDto(String email, int checkNum, boolean certified) {
        this.email = email;
        this.checkNum=checkNum;
        this.certified=certified;
    }
}
