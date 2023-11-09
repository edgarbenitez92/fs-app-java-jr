package com.fullstack.springboot.fsappjavajr.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {

	//	private TodoService todoService;
	private TodoRepository todoRepository;

	public TodoResource(TodoService todoService, TodoRepository todoRepository) {
		//	this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	@GetMapping("users/{user}/todos")
	public List<TodoSummary> retrieveTodos(@PathVariable String user) {
		// return todoService.findTodosByUser(user);
		return todoRepository.getTodosByUser(user);
	}

	@GetMapping("users/{user}/todos/{id}")
	public Todo retrieveTodoById(@PathVariable String user, @PathVariable int id) {
		// return todoService.findTodoById(id);
		return todoRepository.getTodoByUserId(user, id);
	}

	@DeleteMapping("users/{user}/todos/{id}/delete-todo")
	public ResponseEntity<Void> deleteTodoById(@PathVariable String user, @PathVariable int id) {
		// todoService.deleteTodoByUserId(id);
		todoRepository.deleteTodoByUserId(user, id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("users/{user}/update-todo")
	public Todo updateTodo(@PathVariable String user, @RequestBody Todo todo) {
		// todoService.updateTodo(todo);
		// return todo;
		Long id = todo.getId();
		String description = todo.getDescription();
		LocalDate targetDate = todo.getTargetDate();

		return todoRepository.updateTodo(id, user, description, targetDate);
	}

	@PostMapping("users/{user}/create-todo")
	public void createTodo(@PathVariable String user, @RequestBody Todo todo) {
		// return todoService.addTodo(todo.getUser(), todo.getDescription(),
		// todo.getTargetDate(), false);
		todoRepository.createTodo(todo);
	}
}
