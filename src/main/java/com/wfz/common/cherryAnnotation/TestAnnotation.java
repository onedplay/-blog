package com.wfz.common.cherryAnnotation;

import java.lang.reflect.Method;

public class TestAnnotation {
    private static Object CherryAnnotation;

    public static void main(String[] args) throws Exception {
        try {
            Class aClass = Class.forName("com.wfz.common.cherryAnnotation.Student");

            Method studyMethod = aClass.getMethod("study", int.class);

            if (studyMethod.isAnnotationPresent(CherryAnnotation.class)) {
                System.out.println("Student类上配置了CherryAnnotation注解！");

                com.wfz.common.cherryAnnotation.CherryAnnotation cherryAnnotation = studyMethod.getAnnotation(CherryAnnotation.class);
                System.out.println("name: " + cherryAnnotation.name() + ", age: " + cherryAnnotation.age()
                        + ", score: " + cherryAnnotation.score()[0]);
            } else {
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        }catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch(NoSuchMethodException e){
                e.printStackTrace();
            }
        }
    }

