//package com.kcc.restfulservice.controller;
//
//import com.kcc.restfulservice.UserDaoService;
//import com.kcc.restfulservice.bean.Post;
//import com.kcc.restfulservice.bean.User;
//import com.kcc.restfulservice.exception.UserNotFoundException;
//import com.kcc.restfulservice.repository.PostRepository;
//import com.kcc.restfulservice.repository.UserRepository;
//import jakarta.validation.Valid;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@RestController
//@RequestMapping("/jpa")
//public class UserJpaController {
//    private UserRepository userRepository;
//    private PostRepository postRepository;
//
//    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
//        this.userRepository = userRepository;
//        this.postRepository = postRepository;
//    }
//
//    @GetMapping("/users")
//    public List<User> retrieveAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/users/{id}")
//    public EntityModel<User> retrieveUser(@PathVariable int id) {
//        Optional<User> user = userRepository.findById(id);
//
//        if (user == null) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
//
//        // 해태오스
//        EntityModel entityModel = EntityModel.of(user);
//
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//        entityModel.add(linkTo.withRel("all-users"));
//
//        return entityModel;
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//        // Entity 객체를 넣어야 save
//        User savedUser = userRepository.save(user);
//        // location이 header 값에 전달
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedUser.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//        userRepository.deleteById(id);
//    }
//
//    @GetMapping("/users/{id}/posts")
//    public List<Post> retrieveAllPostBy(@PathVariable int id) {
//        Optional<User> user = userRepository.findById(id);
//
//        // isPresent(): 널이 아닌 경우
//        if (!user.isPresent()) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
//
//        return user.get().getPosts();
//    }
//
//    @PostMapping("/users/{id}/posts")
//    public ResponseEntity createPost(@RequestBody Post post, @PathVariable int id) {
//        Optional<User> user = userRepository.findById(id);
//        if (!user.isPresent()) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
////        post.setUser(user.get());
//        postRepository.save(post);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(post.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @GetMapping("/users/{id}/posts/{post_id}")
//    public Post searchPost(@PathVariable int id, @PathVariable int post_id) {
//        Optional<User> user = userRepository.findById(id);
//        Post post = user.get().getPosts().get(post_id - 1);
//
//        if (!user.isPresent()) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
//
//        if (post == null) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", post_id));
//        }
//
//        return post;
//    }
//}
