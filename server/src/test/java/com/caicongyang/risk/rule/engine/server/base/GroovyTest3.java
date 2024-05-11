package com.caicongyang.risk.rule.engine.server.base;

import groovy.lang.GroovyShell;
import org.junit.Test;

public class GroovyTest3 {


    @Test
    public void test3() throws Exception {

        String cmd = "def a = 1; b =1;\n" +
                "return  a==b";
        GroovyShell groovyShell = new GroovyShell();
        Object evaluate = groovyShell.evaluate(cmd);
        System.out.println(evaluate);

    }

    @Test
    public void test4() throws Exception {

        String cmd = "return  1 == 1;";
        GroovyShell groovyShell = new GroovyShell();
        Object evaluate = groovyShell.evaluate(cmd);
        System.out.println(evaluate);

    }


}
