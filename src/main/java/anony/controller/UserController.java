/*
 * File Created: 2021/11/23 14:34:35
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/03 09:23:09
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import anony.entity.User;
import anony.payload.request.UserRequest;
import anony.payload.response.UserResponse;
import anony.repository.UserRepository;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<EntityModel<UserResponse>> get() {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findResponseByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("ser %s not found", userDetails.getUsername())));
        var userEntity = EntityModel.of(user);
        var apiLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HomeController.class).get()).withRel("api");
        userEntity.add(apiLink);
        return ResponseEntity.ok().body(userEntity);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<UserResponse>> post(@Valid @RequestBody UserRequest login) {

        if (userRepository.existsByUsername(login.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("User %s already exist.", login.getUsername()));
        }

        var user = new User(login.getUsername(), passwordEncoder.encode(login.getPassword()));

        userRepository.save(user);

        var userResponse = userRepository.findResponseByUsername(login.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        var userEntity = EntityModel.of(userResponse);
        var apiLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HomeController.class).get()).withRel("api");
        userEntity.add(apiLink);
        return ResponseEntity.ok().body(userEntity);
    }
}
