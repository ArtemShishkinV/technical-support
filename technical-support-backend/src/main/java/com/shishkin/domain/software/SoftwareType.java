package com.shishkin.domain.software;


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
public class SoftwareType extends NamedBasedEntity {
    @OneToMany(mappedBy = "softwareType")
    private Set<Software> software;
}
