package com.shishkin.domain.employee;

import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.application.base.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Notification extends BaseEntity {
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "subscriber_staff_number", referencedColumnName = "staffNumber", nullable = false)
    private Employee subscriber;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id", nullable = false)
    private Priority priority;
}
