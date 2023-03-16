package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional(readOnly = true)
    public List<Post> getPosts() {

        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Optional<Post> getPostId(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않는다.")
        );
        post.getPostId(postRequestDto);
        return postRepository.findById(id);
    }
    @Transactional
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public Optional<Post> update(Long id, PostRequestDto postRequestDto, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않는다.")
        );
        if (post.getPassword().equals(password)){
            post.update(postRequestDto);
            return postRepository.findById(id);
        }
        return null;
    }


    public PostResponseDto delete(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않는다.")
        );
        PostResponseDto result = new PostResponseDto();
        if (post.getPassword().equals(password)) {
            postRepository.deleteById(id);
            result.setResult("success", "게시물이 삭제되었습니다.");
            return result;
        } else {
            result.setResult("failed", "실패입니다");
            return result;
        }
    }
}
