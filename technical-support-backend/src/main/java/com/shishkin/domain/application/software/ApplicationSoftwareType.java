package com.shishkin.domain.application.software;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class ApplicationSoftwareType extends NamedBasedEntity {
    @OneToMany(mappedBy = "applicationSoftwareType")
    private Set<ApplicationSoftware> applicationSoftware;
}
