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

		return todos;
	}

	public void deleteTodoByUserId(String user, int id) {
		String query = environment.getProperty("query.DELETE_TODO_BY_ID");
		jdbcTemplate.update(query, user, id);
	}

	public Todo getTodoByUserId(String user, long id) {
		String query = environment.getProperty("query.FIND_TODO_BY_ID");
		Map<String, Object> todoQuery = jdbcTemplate.queryForMap(query, user, id);

		Todo todo = new Todo();
		todo.setId((int) todoQuery.get("id"));
		todo.setUser((String) todoQuery.get("user_name"));
		todo.setDescription((String) todoQuery.get("description"));
		todo.setDone((boolean) todoQuery.get("done"));

		Date targetDate = (Date) todoQuery.get("targetDate");
		todo.setTargetDate(targetDate.toLocalDate());

		return todo;
	}

	public void createTodo(Todo todo) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String query = environment.getProperty("query.CREATE_TODO");

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, todo.getUser());
			ps.setString(2, todo.getDescription());
			ps.setDate(3, Date.valueOf(todo.getTargetDate()));
			ps.setBoolean(4, false);
			return ps;
		}, keyHolder);
	}

	public Todo updateTodo(Long id, String user, String description, LocalDate targetDate) {
		String query = environment.getProperty("query.UPDATE_TODO");
		jdbcTemplate.update(query, description, targetDate, id);
		return getTodoByUserId(user, id);
	}
}
