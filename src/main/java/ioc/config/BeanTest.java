package ioc.config;


import ioc.bean.Hello;
import ioc.bean.Printer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class BeanTest {

    @Test
    public void simpleBeanScanning() {

        ApplicationContext cx = new AnnotationConfigApplicationContext(
                "ioc.config");
        System.out.println(cx.getBeanDefinitionCount());

        for (String beanDefinitionName : cx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        AnnotatedHello hello = cx.getBean("annotatedHello", AnnotatedHello.class);
        System.out.println(cx.getBeanDefinitionCount());

        System.out.println(hello.hello());
    }

    @Test
    public void configurationBeanScan() {

        ApplicationContext cx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
        AnnotatedHello hello = cx.getBean("annotatedHello", AnnotatedHello.class);
        for (String beanDefinitionName : cx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        AnnotatedHelloConfig config = cx.getBean(AnnotatedHelloConfig.class);
        assert !hello.equals(config.annotatedHello());
    }

    @Test
    public void duplicationBeanTest() {
        ApplicationContext cx = new AnnotationConfigApplicationContext(HelloConfig.class);
        Hello hello = cx.getBean("hello", Hello.class);
        Hello hello2 = cx.getBean("hello2", Hello.class);
        Printer printer = cx.getBean("printer", Printer.class);

        for (String beanDefinitionName : cx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        assert hello.getPrinter().equals(hello2.getPrinter());

    }

    @Test
    public void foolHelloServiceTest() {
        ApplicationContext cx = new AnnotationConfigApplicationContext(FoolHelloService.class);
        Hello hello = cx.getBean("hello", Hello.class);
        Hello hello2 = cx.getBean("hello2", Hello.class);
        Printer printer = cx.getBean("printer", Printer.class);

        hello.print();
        hello2.print();

        for (String beanDefinitionName : cx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(hello.getPrinter().hashCode());
        System.out.println(hello2.getPrinter().hashCode());
        System.out.println(printer.hashCode());

        assert hello.getPrinter().equals(hello2.getPrinter());
    }

    @Test
    public void springDefaultBeanTest () {
        ApplicationContext cx = SpringApplication.run(TestApplication.class);

        System.out.println("🔹 SpringApplication.run()에서 등록된 빈 목록:");
        Arrays.stream(cx.getBeanDefinitionNames()).forEach(System.out::println);

    }
}
