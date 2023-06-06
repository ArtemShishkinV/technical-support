package com.shishkin.repository;

import com.shishkin.domain.application.base.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
