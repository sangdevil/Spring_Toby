package ioc.config;


import ioc.bean.Hello;
import ioc.bean.Printer;
import ioc.bean.StringPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

    // 이런 방식으로 Bean의 의존성 주입을 자바 코드로 하더라도, 실제 동작은 자바 코드로 돌아가지 않는다.
    // 실제로 Printer 빈은 하나만 등록되고 싱글톤으로 공유되므로,
    // Configuration에서의 DI는 java 코드로 DI를 하기 위한 메타 정보로 생각하는 것이 좋다.

    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setName("Spring");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setName("Spring2");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter();
    }
}
