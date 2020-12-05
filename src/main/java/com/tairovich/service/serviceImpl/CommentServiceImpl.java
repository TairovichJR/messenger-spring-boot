package com.tairovich.service.serviceImpl;

import com.tairovich.model.Comment;
import com.tairovich.repository.CommentRepository;
import com.tairovich.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Override
    public Comment getComment(long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Set<Comment> getComments() {
       Set<Comment> comments = new HashSet<>();
       commentRepository.findAll().forEach(comments::add);
       return comments;
    }
}
