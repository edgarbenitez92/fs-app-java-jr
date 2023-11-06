package com.fullstack.springboot.fsappjavajr.todo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TodoResource {

	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("users/{user}/todos")
	public List<Todo> retrieveTodos(@PathVariable String user) {
		return todoService.findTodosByUser(user);
	}
}
