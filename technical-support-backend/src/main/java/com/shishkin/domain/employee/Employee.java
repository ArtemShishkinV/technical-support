package com.shishkin.domain.employee;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.Comment;
import com.shishkin.domain.device.Device;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffNumber;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate birthDay;
    private String phoneNumber;
    private boolean isOnline;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "jobPost_id", referencedColumnName = "id", nullable = false)
    private JobPost jobPost;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;
    @OneToOne
    @JoinColumn(name = "workplace_id", referencedColumnName = "id", unique = true)
    private Workplace workplace;

    @OneToMany(mappedBy = "owner")
    private Set<Device> devices;

    @OneToMany(mappedBy = "sender")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "initiator")
    private Set<Application> applicationsInitiator;

    @OneToMany(mappedBy = "executor")
    private Set<Application> applicationsExecutor;
}
