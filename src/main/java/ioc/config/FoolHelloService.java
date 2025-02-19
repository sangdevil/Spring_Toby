package ioc.config;

import ioc.bean.Hello;
import ioc.bean.Printer;
import ioc.bean.StringPrinter;
import org.springframework.context.annotation.Bean;

public class FoolHelloService {

    // 스프링에서 @Configuration이 없는 경우 CGLIB 프록시를 사용하지 않아서,

    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter();
    }
}
