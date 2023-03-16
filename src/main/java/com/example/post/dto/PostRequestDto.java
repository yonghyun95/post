package com.example.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private String title;
    private String content;
    private String author;
    private String password;

    public PostRequestDto(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }
}
