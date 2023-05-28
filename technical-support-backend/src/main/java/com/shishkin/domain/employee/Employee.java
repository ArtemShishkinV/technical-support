package com.shishkin.domain.employee;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.Comment;
import com.shishkin.domain.device.Device;
import lombok.Data;

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
import java.util.Set;

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
    private List<Device> devices;

    @OneToMany(mappedBy = "sender")
    private List<Comment> comments;

    @OneToMany(mappedBy = "initiator")
    private List<Application> applicationsInitiator;

    @OneToMany(mappedBy = "executor")
    private List<Application> applicationsExecutor;
}
