package com.example.bonggonge.dto;


import com.example.bonggonge.domain.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class JwtDto implements UserDetails {
    private Long no;
    private String username;
    private String email;
    private String nickname;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    @Builder
    public JwtDto(Long no,String username, String nickname, String email, String password) {
        this.no=no;
        this.username=username;
        this.nickname=nickname;
        this.email = email;
        this.password = password;
    }
}