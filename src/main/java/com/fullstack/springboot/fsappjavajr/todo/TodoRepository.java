package com.fullstack.springboot.fsappjavajr.todo;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TodoRepository(Environment environment) {
		this.environment = environment;
	}

	public List<Todo> getTodosByUser(String user) {
		String query = environment.getProperty("query.FIND_TODOS_BY_USER");
		List<Todo> todos = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Todo.class), user);
		return todos;
	}

	public void deleteTodoByUserId(String user, int id) {
		String query = environment.getProperty("query.DELETE_TODO_BY_ID");
		jdbcTemplate.update(query, user, id);
	}

	public Todo getTodoByUserId(String user, int id) {
		String query = environment.getProperty("query.FIND_TODO_BY_ID");
		Todo todo = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Todo.class), user, id);
		return todo;
	}
}
