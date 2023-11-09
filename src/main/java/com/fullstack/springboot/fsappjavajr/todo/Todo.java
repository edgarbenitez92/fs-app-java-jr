package com.fullstack.springboot.fsappjavajr.todo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "TODO_DETAILS")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String user_name;
	private String description;
	private LocalDate targetDate;
	@Column(name = "done")
	private boolean isDone;

	public Todo() {

	}

	public Todo(int id, String user_name, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = done;
	}

	public String getUser() {
		return user_name;
	}

	public void setUser(String user_name) {
		this.user_name = user_name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean done) {
		this.isDone = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", user_name=" + user_name + ", description=" + description + ", targetDate="
				+ targetDate + ", isDone=" + isDone + "]";
	}
}
