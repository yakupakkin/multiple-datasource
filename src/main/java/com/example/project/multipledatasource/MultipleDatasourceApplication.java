package com.example.project.multipledatasource;

import com.example.project.multipledatasource.repository.postgres.CarRepository;
import com.example.project.multipledatasource.repository.postgres.EmployeeRepository;
import com.example.project.multipledatasource.repository.testdb.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class MultipleDatasourceApplication implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(employeeRepository.findById(1).get().getName());
        System.out.println(personRepository.findById(1).get().getName());
        System.out.println(carRepository.findById(1).get().getBrand());

    }
}
