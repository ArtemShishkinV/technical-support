package com.shishkin.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public abstract class NamedBasedEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String title;
}
