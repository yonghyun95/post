package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts")
    public List<Post> getPost() {
        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Post> getPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.getPostId(id,postRequestDto);
    }


    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

    @PutMapping("/api/post/{id}")
    public Optional<Post> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.update(id, postRequestDto, postRequestDto.getPassword());
    }

    @DeleteMapping("/api/post/{id}")
    public PostResponseDto deletedPost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.delete(id, postRequestDto.getPassword());
    }

}
