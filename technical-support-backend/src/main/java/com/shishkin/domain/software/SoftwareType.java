package com.shishkin.domain.software;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SoftwareType extends NamedBasedEntity {
    private String icon;
    @JsonIgnore
    @OneToMany(mappedBy = "softwareType")
    private Set<Software> software;
}
