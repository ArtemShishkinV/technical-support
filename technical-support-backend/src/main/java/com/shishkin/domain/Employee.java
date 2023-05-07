package com.shishkin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long staffNumber;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate birthDay;
    private String phoneNumber;
    private String role;

    public Role getRole() {
        return Role.getByCode(role);
    }
}
