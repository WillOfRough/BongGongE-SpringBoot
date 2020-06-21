package com.example.bonggonge.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "factory")
@ToString
public class FactoryEntity {

    @Id
    @Column(name = "factory_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @JoinColumn(name = "user_no",nullable = false)
    private Long userNo;

    @Column(name = "factory_name",nullable = false)
    private String factoryName;

    @Column(name = "detail",nullable = false)
    private String detail;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @Column(name = "longitude",nullable = false)
    private double longitude;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "UIimage",nullable = false)
    private String UIimage;

    @Builder
    public FactoryEntity(Long no, Long userNo, String factoryName, double latitude, double longitude,
                         String address,String detail,String phone,String UIimage) {
        this.no = no;
        this.userNo=userNo;
        this.factoryName=factoryName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detail = detail;
        this.phone = phone;
        this.UIimage = UIimage;
    }

}
