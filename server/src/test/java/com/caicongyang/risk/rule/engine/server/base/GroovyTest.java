package com.caicongyang.risk.rule.engine.server.base;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class GroovyTest {


    public static void main(String[] args) throws Exception {
        GroovyClassLoader loader = new GroovyClassLoader();
        // java代码
        String java = " " +
                "   public class Test { " +
                "      public int add(double a, double b) { " +
                "        double sum = a + b;  " +
                "         System.out.println(\"Script sum=\" + sum);  " +
                "         return sum.intValue(); " +
                "      }  " +
                "  } ";
        Class scriptClass = loader.parseClass(java);
        GroovyObject scriptInstance = (GroovyObject) scriptClass.getDeclaredConstructor().newInstance();
        Object result = scriptInstance.invokeMethod("add", new Object[]{1, 2});
        System.out.println("Groovy result=" + result);


    }


}
