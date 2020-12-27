package com.orange;

import com.orange.service.SomeService;
import com.orange.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test01(){
        SomeService service = new SomeServiceImpl();
        service.doSome();
    }

    @Test
    public void test02(){
        String config = "beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        SomeService service1 = (SomeService) ac.getBean("service1");
        service1.doSome();
    }
}
