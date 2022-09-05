package org.testing.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {


    @BeforeTest
    public void setUp() {
        System.out.println("This test is started");
    }

    @AfterTest
    public void tearDown() {
        System.out.println("This test is completed");
    }
}

