package com.caicongyang.risk.rule.engine.server.base;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class GroovyTest2 {


    public static void main(String[] args) throws Exception {
        String groovy = " def call(int a,int b) { " +
                "   return a + b " +
                "}";

        GroovyClassLoader loader = new GroovyClassLoader();
        Class scriptClass = loader.parseClass(groovy);
        GroovyObject scriptInstance = (GroovyObject)         scriptClass.newInstance();
        scriptInstance.invokeMethod("call", new Object[]{1, 2})
        System.out.println("Groovy result=" + result);
    }


}
