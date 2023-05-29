package com.shishkin.domain.application.software;

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
public class ApplicationSoftwareType extends NamedBasedEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "applicationSoftwareType")
    private Set<ApplicationSoftware> applicationSoftware;
}
