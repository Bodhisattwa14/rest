package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserAPI userapi;

	@Autowired
	PostAPI postapi;

	public List<User> getUsers() {
		return userapi.findAll();
	}

	public Optional<User> getOneUser(int id) throws UserNotFoundException {
		Optional<User> user1 = userapi.findById(id);
		
		
		return user1;

	}

	public User createUser(User user) {

		userapi.save(user);

		return user;
	}

	public Post createPost(Post post, int userid) {

		Optional<User> member1 = userapi.findById(userid);

		post.setUser(member1.get());

		member1.get().addPost(post);

		postapi.save(post);

		return post;
	}

	public List<Post> getAllPost(int userid) {

		Optional<User> member1 = userapi.findById(userid);

		List<Post> posts = member1.get().getPosts();

		return posts;
	}

}
