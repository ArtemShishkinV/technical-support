package com.shishkin.domain.software;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Software extends NamedBasedEntity {
    private String description;
    @ManyToOne
    @JoinColumn(name = "softwareType_id", referencedColumnName = "id")
    private SoftwareType softwareType;
}
