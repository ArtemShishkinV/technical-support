package com.shishkin.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class NamedBasedEntity extends BaseEntity {
    private String title;
}
