package com.example.bonggonge.service.sign;


import com.example.bonggonge.domain.entity.EmailEntity;
import com.example.bonggonge.domain.repository.EmailRepository;
import com.example.bonggonge.dto.EmailDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//수정
@Service
@AllArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    public final JavaMailSender javaMailSender;

    @Async
    public boolean sendEmail(String email) {
        int emailRandom = (int)(Math.random() * 899 +100);
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        // simpleMessage.setFrom("보낸사람@naver.com"); // NAVER, DAUM, NATE일 경우 넣어줘야 함
        simpleMessage.setTo(email);
        simpleMessage.setSubject("이메일 인증");
        simpleMessage.setText("인증번호 : " + emailRandom);
        javaMailSender.send(simpleMessage);

        EmailDto email_dto = EmailDto.builder()
                .email(email)
                .checkNum(emailRandom)
                .build();
        emailRepository.save(email_dto.toEntity());
        return true;
    }
//    @Transactional
//    public boolean authEmail(String email,String math){
//        List<EmailEntity> emailEntityWrapper = emailRepository.findByEmail(email);
//        if(emailEntityWrapper.size()>0){
//            if(Integer.toString(emailEntityWrapper.get(0).getCheckNum()).equals(math)){
//                emailRepository.updateByEmail(email);
//                return true;
//            }
//            else {
//                return false;
//            }
//        }else{
//            return false;
//        }
//
//    }
}
