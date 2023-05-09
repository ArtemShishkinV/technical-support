package com.shishkin.domain.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
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
    private String role;

    @ManyToOne
    @JoinColumn(name = "jobPost_id", referencedColumnName = "id")
    private JobPost jobPost;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @OneToOne
    @JoinColumn(name = "workplace_id", referencedColumnName = "id", unique = true)
    private Workplace workplace;
    public Role getRole() {
        return Role.getByCode(role);
    }
}
