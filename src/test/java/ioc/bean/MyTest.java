package ioc.bean;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class MyTest {


    @Test
    public void RegisterBeanTest() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);

        Hello hello1 = ac.getBean("hello1", Hello.class);
        Assertions.assertNotNull(hello1);

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        ac.registerBeanDefinition("hello2", helloDef);

        Hello hello2 = ac.getBean("hello2", Hello.class);
        Assertions.assertEquals(hello2.sayHello(), "Hello Spring");
        Assertions.assertNotEquals(hello1, hello2);
        Assertions.assertEquals(ac.getBeanFactory().getBeanDefinitionCount(), 2);
    }

    @Test
    public void RegisterBeanWithDependency() {
        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        helloDef.getPropertyValues().addPropertyValue("printer",
                new RuntimeBeanReference("printer"));

        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        Assertions.assertEquals(ac.getBean("printer").toString(), "Hello Spring");
    }


}