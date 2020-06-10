package com.example.bonggonge.dto;

import com.example.bonggonge.domain.entity.FactoryEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FactoryDto {
    private Long userNo;
    private String factoryName;
    private int factoryLat;
    private int factoryLng;


    public FactoryEntity toEntity(){
        return FactoryEntity.builder()
                .userNo(userNo)
                .factoryName(factoryName)
                .factoryLat(factoryLat)
                .factoryLng(factoryLng)
                .build();
    }

    @Builder
    public FactoryDto(Long userNo, String factoryName, int factoryLat, int factoryLng) {
        this.userNo=userNo;
        this.factoryName=factoryName;
        this.factoryLat = factoryLat;
        this.factoryLng = factoryLng;
    }
}