package com.example.kylu.wordpalindrome;

import org.junit.Test;

import static org.junit.Assert.*;


    public class ExampleUnitTest extends MainActivity {

        //

        @Test
        public void isPalindrome_incorrect()
        {
            assertFalse(isPalindrome("bujaka"));
        }
        @Test
        public void isPalindrome_correct()
        {
            assertTrue(isPalindrome("K ajak"));
        }
    }
