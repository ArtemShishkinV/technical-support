package com.shishkin.domain.employee;

import com.shishkin.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
// Создание ограничения уникальности для совокупности столбцов
// так как одно и тоже рабочее место
// не может быть в одном офисе на одном этаже и одной комнате
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"office_id", "floor", "room_number", "table_number"})
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Workplace extends BaseEntity {
    @Column(nullable = false)
    private int floor;
    @Column(nullable = false)
    private String roomNumber;
    @Column(nullable = false)
    private int tableNumber;

    // Связь с сущностью Office, которая создаст внешний ключ
    // добавив столбец office_id в таблицу Workplace
    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;
    @OneToOne(mappedBy = "workplace")
    private Employee employee;
}
