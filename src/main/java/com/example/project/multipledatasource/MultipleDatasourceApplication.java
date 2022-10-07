package com.example.project.multipledatasource;

import com.example.project.multipledatasource.entity.Employee;
import com.example.project.multipledatasource.entity.Person;
import com.example.project.multipledatasource.repository.employee.EmployeeRepository;
import com.example.project.multipledatasource.repository.person.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Log4j2
@SpringBootApplication
public class MultipleDatasourceApplication implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("", employeeRepository.save(new Employee(1,"John Doe")));
        log.info("", personRepository.save(new Person(1,"John Doe")));
    }
}
