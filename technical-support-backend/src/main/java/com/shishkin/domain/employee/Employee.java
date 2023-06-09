package com.shishkin.domain.employee;

import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.application.base.Comment;
import com.shishkin.domain.device.Device;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_number_generator")
    @SequenceGenerator(
            name = "staff_number_generator",
            sequenceName = "employee_seq",
            initialValue = 10000000,
            allocationSize = 1
    )
    private Long staffNumber;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private LocalDate birthDay;

    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String tgChatId;
    @Column(nullable = false, columnDefinition = "boolean default false")
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
    private List<Device> devices;

    @OneToMany(mappedBy = "sender")
    private List<Comment> comments;

    @OneToMany(mappedBy = "subscriber")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "initiator")
    private List<Application> applicationsInitiator;

    @OneToMany(mappedBy = "executor")
    private List<Application> applicationsExecutor;
}
