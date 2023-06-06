package com.shishkin.repository;

import com.shishkin.domain.application.base.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
