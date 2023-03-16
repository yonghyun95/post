package com.example.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {

    private String result;
    private String message;

    public void setResult(String result, String message) {
        this.result = result;
        this.message = message;
    }

}
