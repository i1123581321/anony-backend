package anony.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import anony.entity.User;
import anony.payload.request.UserRequest;
import anony.projection.UserProjection;
import anony.repository.UserRepository;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public EntityModel<UserProjection> get() {
        // get user details
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
        var user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException(String.format("%s not found", userDetails.getUsername())));
        var userProjection = new UserProjection(user.getUsername(), user.getCreateOn(), user.getUpdateOn());
        var userEntity = EntityModel.of(userProjection);
        var apiLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HomeController.class).get()).withRel("api");
        userEntity.add(apiLink);
        return userEntity;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<UserProjection> post(@Valid @RequestBody UserRequest login) {

        if (Boolean.TRUE.equals(userRepository.existsByUsername(login.username()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("User %s already exist.", login.password()));
        }

        var user = new User(login.username(), passwordEncoder.encode(login.password()));

        var savedUser = userRepository.save(user);
        var userProjection = new UserProjection(savedUser.getUsername(), savedUser.getCreateOn(), savedUser.getUpdateOn());

        var userEntity = EntityModel.of(userProjection);
        var apiLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(HomeController.class).get()).withRel("api");
        userEntity.add(apiLink);
        return userEntity;
    }
}
