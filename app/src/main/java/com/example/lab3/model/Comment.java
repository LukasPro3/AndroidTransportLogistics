package com.example.lab3.model;


import java.time.LocalDateTime;
import java.util.List;


public class Comment{

    private int id;
    private String title;
    private String text;
    private LocalDateTime creationTime;

    private List<Comment> replies;

    private Comment parentComment;
    private Forum forum;
}
