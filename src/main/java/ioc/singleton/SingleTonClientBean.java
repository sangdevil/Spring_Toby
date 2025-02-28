package ioc.singleton;

import org.springframework.beans.factory.annotation.Autowired;

public class SingleTonClientBean {

    @Autowired
    SingletonBean bean1;

    @Autowired
    SingletonBean bean2;
}
