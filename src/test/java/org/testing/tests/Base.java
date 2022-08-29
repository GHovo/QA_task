package org.testing.tests;

import org.testing.sources.PropertiesReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {

    protected PropertiesReader apiProperties = new PropertiesReader();


    @BeforeTest
    public void setUp() {
        System.out.println("This test is started");
    }

    @AfterTest
    public void tearDown() {
        System.out.println("This test is completed");
    }
}

