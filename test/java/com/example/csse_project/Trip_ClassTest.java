package com.example.csse_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

class Trip_ClassTest {

    @Test
    void getFee() {
            Trip_Class u = new Trip_Class(2000);
            assertEquals(2000,u.getFee());
        }




    @Test
    void setFee() {
        fail();
    }
}