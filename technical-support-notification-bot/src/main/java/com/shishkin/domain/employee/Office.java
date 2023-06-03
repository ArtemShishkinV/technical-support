package com.shishkin.domain.employee;

import com.shishkin.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Office extends BaseEntity {
    private String city;
    private String street;
    private int buildNumber;

    @OneToMany(mappedBy = "office")
    private Set<Workplace> workplaces;
}
