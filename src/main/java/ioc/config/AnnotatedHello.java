package ioc.config;


import org.springframework.stereotype.Component;

@Component
public class AnnotatedHello {

    public String hello() {
        return "Hello World!";
    }
}
