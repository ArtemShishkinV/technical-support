package com.shishkin.domain.employee;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Department extends NamedBasedEntity {
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
