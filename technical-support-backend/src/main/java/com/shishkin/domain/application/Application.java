package com.shishkin.domain.application;


import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.employee.Employee;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Application extends BaseEntity {
    private String description;
    private boolean isOffline;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime solvedAt;
    private LocalDateTime appointmentAt;

    @OneToOne(mappedBy = "application")
    private ApplicationDevice applicationDevice;

    @OneToOne(mappedBy = "application")
    private ApplicationSoftware applicationSoftware;

    @OneToMany(mappedBy = "application")
    private Set<Attachment> attachments;

    @OneToMany(mappedBy = "application")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "initiatorStaffNumber", referencedColumnName = "staffNumber")
    private Employee initiator;

    @ManyToOne
    @JoinColumn(name = "executorStaffNumber", referencedColumnName = "staffNumber")
    private Employee executor;
}
