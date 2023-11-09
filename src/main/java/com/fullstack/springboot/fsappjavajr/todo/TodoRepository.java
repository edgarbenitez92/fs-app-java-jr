package com.fullstack.springboot.fsappjavajr.todo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TodoRepository(Environment environment) {
		this.environment = environment;
	}

	public List<TodoSummary> getTodosByUser(String user) {
		String query = environment.getProperty("query.FIND_TODOS_BY_USER");
		List<Map<String, Object>> todosQuery = jdbcTemplate.queryForList(query, user);

		List<TodoSummary> todos = todosQuery.stream().map(row -> {
			TodoSummary todo = new TodoSummary();
			todo.setId((int) row.get("id"));
			todo.setDescription((String) row.get("description"));
			todo.setDone((boolean) row.get("done"));

			Date targetDate = (Date) row.get("targetdate");
			todo.setTargetDate(targetDate.toLocalDate());
			return todo;
		}).collect(Collectors.toList());

		System.out.println("Todos: " + todos);
		return todos;
	}

	public void deleteTodoByUserId(String user, int id) {
		String query = environment.getProperty("query.DELETE_TODO_BY_ID");
		jdbcTemplate.update(query, user, id);
	}

	public Todo getTodoByUserId(String user, long id) {
		String query = environment.getProperty("query.FIND_TODO_BY_ID");
		Todo todo = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Todo.class), user, id);
		return todo;
	}
}
