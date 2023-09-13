package org.hahunavth.springcore;

import org.hahunavth.springcore.transaction.BookConfig;
import org.hahunavth.springcore.transaction.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {
    @Test
    public void testTransaction() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BookConfig.class);
        context.registerShutdownHook();

        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.deleteAll();
        bookService.saveListBook();

        context.close();
    }
}
