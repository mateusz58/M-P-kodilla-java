package com.kodilla.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kodilla.hibernate.company.Company;
import com.kodilla.hibernate.company.Employee;
import com.kodilla.hibernate.dao.CompanyDao;
import com.kodilla.hibernate.generators.WordGenerator;
import java.util.Collections;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CompanyDaoTestSuite {

    @Autowired
    CompanyDao companyDao;

    Employee employee;
    Employee employee2;

    Company companyToSave;
    Company company2;

    @BeforeEach
    void setUp() {
        employee = Employee.Builder.anEmployee()
                .firstName(WordGenerator.generateRandomWord())
                .lastName(WordGenerator.generateRandomWord())
                .build();
        employee2 = Employee.Builder.anEmployee()
                .firstName(WordGenerator.generateRandomWord())
                .lastName(WordGenerator.generateRandomWord())
                .build();
        companyToSave = Company.Builder.aCompany()
                .name(WordGenerator.generateRandomWord())
                .employees(Collections.singletonList(employee))
                .build();
        company2 = Company.Builder.aCompany()
                .name(WordGenerator.generateRandomWord())
                .employees(Collections.singletonList(employee2))
                .build();
        companyDao.save(company2);
    }

    @AfterEach
    void cleanUp() {
        companyDao.deleteAll();
    }

    @Test
    public void shouldSave() {
        //Given
        Company given = companyToSave;

        //When
        Company actual = companyDao.save(given);

        //Then
        assertEquals(given, actual);
    }

    @Test
    public void shouldReturnCompanyByLetters() {
        //Given
        Company given = company2;

        //When
        Company actual = companyDao.findCompanyByLetters(given.getCompanyName().substring(0,3).trim()).stream().findFirst().get();

        //Then
        assertEquals(given, actual);
    }

    @Test
    public void shouldReturnCompanyByName() {
        //Given
        Company given = company2;

        //When
        Company actual = companyDao.findCompanyByName(given.getCompanyName()).stream().findFirst().get();

        //Then
        assertEquals(given, actual);
    }
}
