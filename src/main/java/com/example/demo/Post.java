package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue
	int id;
	String details;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	User user;
	
	
	

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Post(int id, String details, User user) {
		super();
		this.id = id;
		this.details = details;
		this.user = user;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", details=" + details + "]";
	}
	
	
	
	
	
}
