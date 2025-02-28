package ioc.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class SingleTonTest {


    @Test
    public void singletonScope() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                SingletonBean.class, SingleTonClientBean.class
        );

        Set<SingletonBean> beans = new HashSet<>();

        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingleTonClientBean.class).bean1);
        beans.add(ac.getBean(SingleTonClientBean.class).bean2);
        Assertions.assertEquals(beans.size(), 1);

    }

    @Test
    public void prototypeScope() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                PrototypeBean.class, PrototypeClientBean.class);
        Set<PrototypeBean> beans = new HashSet<>();

        beans.add(ac.getBean(PrototypeBean.class));
        beans.add(ac.getBean(PrototypeBean.class));
        beans.add(ac.getBean(PrototypeClientBean.class).bean1);
        beans.add(ac.getBean(PrototypeClientBean.class).bean2);
        Assertions.assertEquals(beans.size(), 4);
    }
}
