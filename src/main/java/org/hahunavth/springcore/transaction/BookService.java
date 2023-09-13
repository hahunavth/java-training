package org.hahunavth.springcore.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void saveListBook() {
        for(int i = 1; i < 10; i++) {
            bookRepository.save(i, "Book " + i, "Author " + i);

            if (i == 5) {
                throw new NullPointerException("Error");
            }
        }
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
