package com.example.project.multipledatasource.repository.postgres;

import com.example.project.multipledatasource.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
}
