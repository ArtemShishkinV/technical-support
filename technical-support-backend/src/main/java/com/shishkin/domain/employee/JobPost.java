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
@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class JobPost extends NamedBasedEntity {
    @OneToMany(mappedBy = "jobPost")
    private Set<Employee> employees;
}
