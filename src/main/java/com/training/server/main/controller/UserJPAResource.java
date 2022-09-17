package com.training.server.main.controller;

import com.training.server.main.dao.UserRepository;
import com.training.server.main.dao.PostRepository;
import com.training.server.main.exceptions.UserNotFoundException;
import com.training.server.main.model.Post;
import com.training.server.main.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@org.springframework.web.bind.annotation.RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User1> retriveAllUsers() {
        //return service.findAll();
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User1> retriveUser(@PathVariable int id) {
        Optional<User1> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id: " + id);

        EntityModel<User1> entityModel = EntityModel.of(user.get());
        Link link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retriveAllUsers()).withRel("user-list");
        entityModel.add(link);

        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User1 user) {
        User1 sevedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //method that delete a user resource
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retriveAllUsers(@PathVariable int id) {
        Optional<User1> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id: " + id);
        }
        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post) {
        Optional<User1> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id: " + id);
        }
        User1 user = userOptional.get();
        //map the user to the post
        post.setUser1(user);
        //save post to the database
        postRepository.save(post);
        //getting the path of the post and append id of the post to the URI
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        //returns the location of the created post
        return ResponseEntity.created(location).build();
    }
}
