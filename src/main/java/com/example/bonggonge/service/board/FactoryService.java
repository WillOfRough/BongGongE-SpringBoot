package com.example.bonggonge.service.board;

import com.example.bonggonge.domain.repository.FactoryRepository;
import com.example.bonggonge.dto.FactoryDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;
    @Transactional
    public boolean EditFactory(Long userNo,String factoryName,int factoryLng,int factoryLat) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();     // 비밀번호 암호화

            FactoryDto factoryDto = FactoryDto.builder()
                    .userNo(userNo)
                    .factoryName(factoryName)
                    .factoryLng(factoryLng)
                    .factoryLat(factoryLat)
                    .build();

        factoryRepository.save((factoryDto.toEntity()));
            return true;
    }
}
