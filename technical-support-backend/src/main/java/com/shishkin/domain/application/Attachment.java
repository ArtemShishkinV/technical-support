package com.shishkin.domain.application;

import com.shishkin.domain.NamedBasedEntity;
import com.shishkin.domain.employee.Employee;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Getter
@ToString
public class Attachment extends NamedBasedEntity {
    private byte[] file;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sender_staffNumber", referencedColumnName = "staffNumber")
    private Employee sender;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;
}
