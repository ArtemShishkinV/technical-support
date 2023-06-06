package com.shishkin.domain.application.software;

import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.software.Software;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ApplicationSoftware extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "software_id", referencedColumnName = "id")
    private Software software;

    @ManyToOne
    @JoinColumn(name = "applicationSoftwareType_id", referencedColumnName = "id")
    private ApplicationSoftwareType applicationSoftwareType;
}
