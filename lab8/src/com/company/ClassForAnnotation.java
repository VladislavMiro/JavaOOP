package com.company;

import java.lang.reflect.Method;

public class ClassForAnnotation {

    @Repeat(value = 1)
    private void firstMethod() {
        System.out.println("First method is working " + "times!");
    }

    @Repeat(value = 2)
    private void secondMethod() {
        System.out.println("Second method is working " + "times!");
    }

    @Repeat(value = 3)
    private void thirdMethod() { System.out.println("Third method is working "  + "times!"); }

    private void fourthMethod() { System.out.println("Fourth method is working " + "times!"); }

}
