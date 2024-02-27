package com.projet.ecommerce.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projet.ecommerce.entities.User;

import com.projet.ecommerce.service.IuserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
	IuserService userv;
	
	
    @GetMapping("/users") // afficher all user 
	public List<User> getAllUsers() {
		List<User> pro = userv.findAllUsers();

        return pro;
	    
	}

	@PostMapping("/addusert") // add user
	public User createUser(@Valid @RequestBody User user) {
	    return userv.saveUser(user);
	}
    
    @GetMapping("/users/{id}") // afficher user by id
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userv.findUserById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/{id}")  //modifier user byid
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User user = userv.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")  //effacer un user
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userv.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/with-commands")  //personnes qui ont passé des commd
    public ResponseEntity<List<User>> getUsersWithCommands() {
        List<User> users = userv.getUsersWithCommands();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserWithCommandsAndProducts(@PathVariable Long userId) {
        User user = userv.getUserWithCommandsAndProducts(userId);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
