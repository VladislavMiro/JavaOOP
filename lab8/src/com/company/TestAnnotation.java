package com.company;

import java.lang.reflect.Method;

public class TestAnnotation {

    public void executeMethods() {
        ClassForAnnotation cl = new ClassForAnnotation();
        Class<?> clas = cl.getClass();
        for (Method met: clas.getDeclaredMethods()) {
            if (met.isAnnotationPresent(Repeat.class)) {
                met.setAccessible(true);
                for(int i=0; i < met.getAnnotation(Repeat.class).value(); i++) {
                    try {
                        met.invoke(cl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
