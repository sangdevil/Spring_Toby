package ioc.bean;

import org.springframework.stereotype.Component;

@Component
class C {
    public String getMessage() {
        return "Hello from C";
    }

    C() {
        System.out.println("C Created");
    }
}
