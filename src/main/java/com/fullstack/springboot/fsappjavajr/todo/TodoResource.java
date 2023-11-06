package com.fullstack.springboot.fsappjavajr.todo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {

	private TodoService todoService;

	public TodoResource(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("useres/{user}/todos")
	public List<Todo> retrieveTodos(@PathVariable String user) {
		return todoService.findTodosByUser(user);
	}
}
