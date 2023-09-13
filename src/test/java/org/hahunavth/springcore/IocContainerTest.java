package org.hahunavth.springcore;

import org.hahunavth.springcore.ioccontainer.javabased.AccountConfiguration;
import org.hahunavth.springcore.ioccontainer.javabased.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocContainerTest {

    // err
    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // populate the factory with bean definitions

        // now register any needed BeanPostProcessor instances
        factory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("/springcore/ioccontainer/account-bean-config.xml");

        AccountService accountService = factory.getBean(AccountService.class);
        System.out.println(accountService);
    }

    @Test
    public void testJavaBased() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AccountConfiguration.class);
        AccountService account = context.getBean(AccountService.class);
        System.out.println(account);
        Assertions.assertNotNull(account);
    }

    @Test
    public void testAnnotationBased() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/springcore/ioccontainer/user-bean-config.xml");
        AccountService accountService = context.getBean(AccountService.class);
        System.out.println(accountService);
        Assertions.assertNotNull(accountService);
    }

    @Test
    public void XmlBased() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/springcore/ioccontainer/account-bean-config.xml");
        AccountService accountService = context.getBean(AccountService.class);
        System.out.println(accountService);
        Assertions.assertNotNull(accountService);
    }
}
