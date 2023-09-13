package org.hahunavth.springcore.aop;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcut {

    @Pointcut("execution(* org.hahunavth.springcore.aop.A.m1())")
    private void m1() {
        System.out.println("MyPointcut.m1");
    }

    public static void main(String[] args) {
        new MyPointcut().m1();
    }
}

class A {
    public void m1 () {
        System.out.println("A.m1");
    }
}