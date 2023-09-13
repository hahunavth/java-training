package org.hahunavth.springcore.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(int id, String name, String author) {
        /**
         * create table book (
         *    id int primary key,
         *    name varchar(50),
         *    author varchar(50)
         *    );
         */
        String sql = "insert into book (id, name, author) values (?, ?, ?)";
        jdbcTemplate.update(sql, id, name, author);
    }

    public void deleteAll() {
        String sql = "delete from book";
        jdbcTemplate.update(sql);
    }
}
