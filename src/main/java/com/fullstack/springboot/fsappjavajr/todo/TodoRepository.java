package com.fullstack.springboot.fsappjavajr.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String FIND_TODOS_BY_USER = """
			SELECT * FROM TODO_DETAILS WHERE user_name = ?
			""";

	public List<Todo> getTodosByUser(String user) {
		return jdbcTemplate.query(FIND_TODOS_BY_USER, new BeanPropertyRowMapper<>(Todo.class), user);
	}
}
