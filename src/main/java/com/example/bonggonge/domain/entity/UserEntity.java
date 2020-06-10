package com.example.bonggonge.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long no;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 20)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 300, nullable = false)
    private String password;

    @Builder
    public UserEntity(Long no, String username, String nickname, String email, String password) {
        this.no = no;
        this.username=username;
        this.nickname=nickname;
        this.email = email;
        this.password = password;
    }
}
