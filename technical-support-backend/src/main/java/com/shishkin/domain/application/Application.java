package com.shishkin.domain.application;


import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Application extends BaseEntity {
    private String description;
    private boolean isOffline;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date solvedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationAt;

    @OneToMany(mappedBy = "application")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "staffNumber", name = "initiator_staffNumber")
    private Employee initiator;
    @ManyToOne
    @JoinColumn(referencedColumnName = "staffNumber", name = "executor_staffNumber")
    private Employee executor;
}
