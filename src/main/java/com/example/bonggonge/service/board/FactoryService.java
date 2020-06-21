package com.example.bonggonge.service.board;

import com.example.bonggonge.domain.entity.FactoryEntity;
import com.example.bonggonge.domain.repository.FactoryRepository;
import com.example.bonggonge.dto.FactoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FactoryService {


    private final FactoryRepository factoryRepository;
    @Transactional
    public boolean EditFactory(Long userNo,String detail,String address,String factoryName,double latitude,double longitude,
                               String phone,String UIimage) {

        FactoryDto factoryDto = FactoryDto.builder()
                .userNo(userNo)
                .factoryName(factoryName)
                .detail(detail)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .phone(phone)
                .UIimage(UIimage)
                .build();

        factoryRepository.save((factoryDto.toEntity()));
        return true;
    }

    @Transactional
    public List<FactoryEntity> ListFactory(double distance,double latitude,double longitude){
        List<FactoryEntity> factoryEntities = factoryRepository.listFactory(distance,latitude,longitude);
        return  factoryEntities;
    }
}
