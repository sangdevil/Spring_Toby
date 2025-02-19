package ioc.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class A {
    private final B b;

    @Autowired
    public A(B b) { // B가 주입됨
        System.out.println("A Created");
        this.b = b;
    }

    public void printMessage() {
        System.out.println(b.getCMessage());
    }
}