package com.kchmurowicz.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class TaskList implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NonNull
	private String name;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy="taskList")
	private List <Task> tasks = new ArrayList<>();



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TaskList{" +
				"id=" + id +
				", name='" + name + '\'' +
				", tasks=" + tasks +
				'}';
	}
}
