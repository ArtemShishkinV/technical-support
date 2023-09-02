package com.shishkin.domain;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class NamedBasedEntity extends BaseEntity {
    @NaturalId
    private String title;
}
