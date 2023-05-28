package com.shishkin.domain.application;

import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Comment extends BaseEntity {
    private String text;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;
    @ManyToOne
    @JoinColumn(name = "sender_staffNumber", referencedColumnName = "staffNumber")
    private Employee sender;
}
