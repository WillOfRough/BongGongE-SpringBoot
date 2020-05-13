package com.example.bonggonge.service.sign;


import com.example.bonggonge.config.jwt.JwtTokenUtil;
import com.example.bonggonge.domain.entity.EmailEntity;
import com.example.bonggonge.domain.entity.UserEntity;
import com.example.bonggonge.domain.repository.EmailRepository;
import com.example.bonggonge.domain.repository.UserRepository;
import com.example.bonggonge.dto.UserJwtDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {
    private UserRepository userRepository;
    private EmailRepository emailRepository;
    private JwtTokenUtil jwtTokenUtil;

    @Transactional
    public boolean joinUser(String username,String password,String email,String nickName) {
//        List<EmailEntity> emailEntityList = emailRepository.findByEmail(email);
//        if(emailEntityList.isEmpty()){
//            return false;
//        }
//        Optional<UserEntity> userEntityWrapper = userRepository.findByUsername(username);
//        if(emailEntityList.get(0).isCertified()&&!userEntityWrapper.isPresent()){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();     // 비밀번호 암호화

            UserJwtDto userDto = UserJwtDto.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .nickname(nickName)
                    .email(email)
                    .build();

            userRepository.save(userDto.toEntity());
            return true;
//        }
//        return false;
    }

    @Transactional
    public String login(String username,String password) {
        String result="false";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = userRepository.findByUsername(username)                                    //해당 아이디값 추출
                .orElse(UserEntity.builder().build());
        if(userEntity != null) {                                                                            //해당 아이디가 있는지

            System.out.println(userEntity.getNo());
            if (passwordEncoder.matches(password, userEntity.getPassword())) {                              //비밀번호 같은지 확인
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                result = jwtTokenUtil.generateToken(new UserJwtDto(userEntity.getNo(),userEntity.getUsername(), "nickname1", userEntity.getEmail(), userEntity.getPassword()));
            }
        }
        return result;
    }

    @Transactional
    public boolean checkId(String username) {
        Optional<UserEntity> userEntityWrapper = userRepository.findByUsername(username);
        boolean check = userEntityWrapper.isPresent();

        return check;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
