package com.shishkin.domain.application;

import com.shishkin.domain.BaseEntity;
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
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Status extends BaseEntity {
    @OneToMany(mappedBy = "status")
    private Set<Application> applications;
}
