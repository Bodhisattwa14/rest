package com.example.demo;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource ms;

	@GetMapping("/users")
	public List<User> getAll() {
		return userService.getUsers();
	}

	@GetMapping("/users/{userid}")
	public EntityModel<Optional<User>> getOne(@PathVariable int userid) throws Exception {

		Optional<User> user1 =userService.getOneUser(userid);
		if(user1 == null)
			throw new UserNotFoundException("id not found"+userid);
		
		EntityModel<Optional<User>> em = EntityModel.of(user1);
		
		WebMvcLinkBuilder linkto = linkTo(methodOn(this.getClass()).getAll());
		WebMvcLinkBuilder linktoPost = linkTo(methodOn(this.getClass()).getAllPost(userid));
		em.add(linkto.withRel("getALlMembers"));
		em.add(linktoPost.withRel("getAllPostFromUser"));
		return em;
		
		//return user1;
	}

	@PostMapping("/users")
	public ResponseEntity<User> getOne(@Valid @RequestBody User Member) {

		User Member1 = userService.createUser(Member);

		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Member1.getId()).toUri())
				.build();
	}
	
	@GetMapping("/welcome")
	public String welcome() {

		System.out.println(LocaleContextHolder.getLocale());
		return ms.getMessage("good.morning.message", null,"default",LocaleContextHolder.getLocale());
	}
	
	@PostMapping("/posts/{userid}")
	public ResponseEntity<Post> createpost(@PathVariable int userid, @RequestBody Post post) {

		  Post post1 = userService.createPost(post, userid);
		return ResponseEntity.created(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post1.getId()).toUri())
				.build();
	}
	@GetMapping("/posts/{userid}")
	public List<Post> getAllPost(@PathVariable int userid) {

		List<Post> posts =userService.getAllPost(userid);
		return posts;
	}

	
}
