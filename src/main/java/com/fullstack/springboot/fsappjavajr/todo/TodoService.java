package com.fullstack.springboot.fsappjavajr.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;

	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Get AWS Certified", LocalDate.now().plusYears(10), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps", LocalDate.now().plusYears(11), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack Development", LocalDate.now().plusYears(12),
				false));
	}

	public List<Todo> findTodosByUser(String user) {
		Predicate<? super Todo> predicate = todo -> todo.getUser().equalsIgnoreCase(user);
		return todos.stream().filter(predicate).toList();
	}

	public Todo addTodo(String user, String description, LocalDate date, boolean done) {
		Todo todo = new Todo(++todosCount, user, description, date, done);
		todos.add(todo);
		return todo;
	}

	public void deleteTodoByUserId(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findTodoById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteTodoByUserId(todo.getId());
		todos.add(todo);
	}
}
