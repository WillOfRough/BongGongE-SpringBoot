package com.example.bonggonge.dto;

import com.example.bonggonge.domain.entity.FactoryEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FactoryDto {
    private Long no;
    private Long userNo;
    private String factoryName;
    private String detail;
    private String address;
    private double latitude;
    private double longitude;
    private String phone;
    private String UIimage;


    public FactoryEntity toEntity(){
        return FactoryEntity.builder()
                .userNo(userNo)
                .factoryName(factoryName)
                .detail(detail)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .phone(phone)
                .UIimage(UIimage)
                .build();
    }

    @Builder
    public FactoryDto(Long userNo, String detail,String address,String factoryName, double latitude, double longitude,String phone,String UIimage) {
        this.userNo=userNo;
        this.factoryName=factoryName;
        this.detail=detail;
        this.address=address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.UIimage = UIimage;
    }
}
