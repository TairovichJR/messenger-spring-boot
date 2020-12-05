package com.tairovich.service;

import com.tairovich.model.Comment;

import java.util.Set;

public interface CommentService {

    Comment getComment(long id);
    Set<Comment> getComments();
}
