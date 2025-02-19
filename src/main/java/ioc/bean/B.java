package ioc.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class B {
    private final C c;

    @Autowired
    public B(C c) { // C가 주입됨
        System.out.println("B Created");
        this.c = c;
    }

    public String getCMessage() {
        return c.getMessage();
    }
}