package com.bontempo.tddmysqljava.models;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="cars")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="car_name", nullable = false)
    private String carName;

    @Column(name="doors", nullable = false)
    private int doors;

}
