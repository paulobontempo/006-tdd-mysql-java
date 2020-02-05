package com.bontempo.tddmysqljava.repository;

import com.bontempo.tddmysqljava.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
