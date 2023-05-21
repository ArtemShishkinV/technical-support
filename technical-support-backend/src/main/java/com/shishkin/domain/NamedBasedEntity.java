package com.shishkin.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class NamedBasedEntity extends BaseEntity {
    private String title;
}
