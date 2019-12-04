package com.varomir.qa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemoAppTest {

    @Test
    public void testContextLoads() {
        System.out.println(">> Thread_ID: " + Thread.currentThread().getId());
    }
}
