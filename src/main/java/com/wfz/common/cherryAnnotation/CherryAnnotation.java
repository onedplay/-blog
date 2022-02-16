package com.wfz.common.cherryAnnotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface CherryAnnotation {
    String name();
    int age() default 18;
    int[] score();
}

