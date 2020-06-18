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

    @Column(name = "factory_lat",nullable = false)
    private double factoryLat;

    @Column(name = "factory_lng",nullable = false)
    private double factoryLng;


    @Builder
    public FactoryEntity(Long no, Long userNo, String factoryName, double factoryLat, double factoryLng) {
        this.no = no;
        this.userNo=userNo;
        this.factoryName=factoryName;
        this.factoryLat = factoryLat;
        this.factoryLng = factoryLng;
    }

}
