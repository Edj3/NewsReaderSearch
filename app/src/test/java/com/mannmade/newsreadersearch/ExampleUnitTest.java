package com.mannmade.newsreadersearch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void isTautology(){
        assertTrue(true);
    }

    @Test
    @Before
    public void isEJCool(){
        assertNotNull("EJ is cool!");
    }

    @Test
    @After
    public void isEmpty(){
        assertNull(null);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}