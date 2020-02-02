package com.ken207.example2.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CompanyTest {

    @Autowired
    Company company;

    @Test
    public void companyValueTest() {
        //given
        String name = "EXBI";
        String address = "서울";
        String telNumber = "02-1234-1234";
        String email = "master@exbi.co.kr";

        //when

        //then
        System.out.println("company.toString(): " + company);
        assertEquals(name, company.getName());
        assertEquals(address, company.getAddress());
        assertEquals(telNumber, company.getTelNumber());
        assertEquals(email, company.getEmail());
    }
}

