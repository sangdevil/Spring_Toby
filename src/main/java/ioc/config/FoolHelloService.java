package ioc.config;

import ioc.bean.Hello;
import ioc.bean.Printer;
import ioc.bean.StringPrinter;
import org.springframework.context.annotation.Bean;

public class FoolHelloService {

    // 스프링에서 @Configuration이 없는 경우 CGLIB 프록시를 사용하지 않아서,
    // 메서드가 호출될 때마다 직접 새 인스턴스를 생성해서 반환하게 됨.
    // 맨 처음 Printer를 빈으로 등록할 때 새 객체 생성
    // 그 다음에 hello, hello2를 생성할 때 또 새 객체 생성.
    // 또한, hello, hello2에 주입된 Printer 객체는 빈이 아니며,
    // 스프링 컨테이너가 관리하지 않음.

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
