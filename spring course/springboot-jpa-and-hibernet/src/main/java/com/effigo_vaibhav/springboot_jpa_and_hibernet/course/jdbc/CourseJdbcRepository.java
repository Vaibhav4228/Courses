package com.effigo_vaibhav.springboot_jpa_and_hibernet.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static  String INSERT_QUERY =
            """
            INSERT INTO COURSE (ID, NAME, AUTHOR)
            VALUES (?, ?, ?);
            """;
    private static String DELETE_QUERY =
            """
            DELETE FROM COURSE
            WHERE ID = ?;
            """;

    public void insert(int id, String name, String author) {
        springJdbcTemplate.update(INSERT_QUERY, id, name, author);
    }
    public void deleteById(int id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

}
