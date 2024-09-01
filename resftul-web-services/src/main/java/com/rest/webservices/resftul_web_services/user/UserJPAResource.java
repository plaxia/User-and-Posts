package com.rest.webservices.resftul_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.resftul_web_services.jpa.PostRepository;
import com.rest.webservices.resftul_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {

	private UserDaoService service;
	private UserRepository repository;
	private PostRepository postRepository;
	
	
	public UserJPAResource(UserDaoService service,UserRepository repository,PostRepository postRepository) {
		super();
		this.service = service;
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {

		return repository.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUsersById(@PathVariable int id) {

		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id "+id);
		}
		
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers()); 
		EntityModel<Optional<User>> entityModel=EntityModel.of(user);
		entityModel.add(link.withRel("all-users"));
		
	
		return entityModel;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUsersById(@PathVariable int id) {

		 		 repository.deleteById(id);
		
	}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsForUser(@PathVariable int id) {

		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id "+id);
		}
		return user.get().getPost();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id "+id);
		}
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
				return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts/{postid}")
	public Post retrievePostDetailsForUser(@PathVariable int id,@PathVariable int postid) {

		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id "+id);
		}
		
		
		Predicate<? super Post> predicate=post->post.getId()==postid;
		Post post=user.get().getPost().stream().filter(predicate).findFirst().get();	
	return post;
	}
	
	
}
