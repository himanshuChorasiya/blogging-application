package com.hblog.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hblog.api.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
