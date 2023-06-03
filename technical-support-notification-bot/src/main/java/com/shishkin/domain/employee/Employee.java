package com.shishkin.domain.employee;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Employee {
    @Id
    private Long staffNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private LocalDate birthDay;
    private String phoneNumber;
    private String tgChatId;
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
}
