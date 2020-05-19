package com.example.bonggonge.domain.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Getter
@Setter
public class UserEntity {
    @Id
    private String username;
    private String nickname;
    private String email;
    private String password;

    @Builder
    public UserEntity(String username, String nickname, String email, String password,String gender) {
        this.username=username;
        this.nickname=nickname;
        this.email = email;
        this.password = password;
    }
}
