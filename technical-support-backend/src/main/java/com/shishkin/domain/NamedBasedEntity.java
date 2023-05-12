package com.shishkin.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class NamedBasedEntity extends BaseEntity {
    private String title;
}
