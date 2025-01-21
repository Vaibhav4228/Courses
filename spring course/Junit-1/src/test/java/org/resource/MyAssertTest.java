package org.resource;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MyAssertTest {
   List<String> names  = Arrays.asList("vaibhav", "rahul", "elon musk");

   @Test
    void testAssert(){
       boolean test = names.contains("vaibhav");
       boolean test2 = names.contains("elon musk");

       assertTrue(test);
       assertFalse(test2);
       assertArrayEquals(new int[] {1,2}, new int[] {2,1});

       assertEquals(3, names.size());
   }

}
