package com.training.server.main.controller;

import com.training.server.main.dao.UserDaoService;
import com.training.server.main.exceptions.UserNotFoundException;
import com.training.server.main.model.User;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResourceController {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retriveAllUsers() {
        return service.findAll();
    }

    //retrieves a specific user detail
   /* @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id) {
        return service.findOne(id);
    }*/

   /* @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
//runtime exception
            throw new UserNotFoundException("id: " + id);
        return user;
    }*/

    @GetMapping("/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null)
//runtime exception
            throw new UserNotFoundException("id: " + id);
//"all-users", SERVER_PATH + "/users"
//retrieveAllUsers
        /*Resource<User> resource = new Resource<User>(user);   //constructor of Resource class
//add link to retrieve all the users
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
        resource.add(linkTo.withRel("all-users"));*/

        EntityModel<User> entityModel = EntityModel.of(user);
        Link link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retriveAllUsers()).withRel("user-list");
        entityModel.add(link);


        if (user == null) {
            throw new UserNotFoundException("User not found with id : " + id);
        }
        return entityModel;

    }


    //method that posts a new user detail
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }
/*
    //method that posts a new user detail and returns the status of HTTP and location of the user resource
    @PostMapping("/users1")
    public ResponseEntity<Object> createUser1(@RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }*/

    //method that posts a new user detail and returns the status of the user resource
    @PostMapping("/users1")
    public ResponseEntity<Object> createUser1(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //method that delete a user resource
//if the user deleted successfully it returns status 200 OK otherwise 404 Not Found
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null)
//runtime exception
            throw new UserNotFoundException("id: " + id);
    }
}
