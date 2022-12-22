package com.nsearch.hr.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="HrUser")
public class User {

    @Id
    @Column(updatable = false, nullable = false)
    @CsvBindByName
    private String id;

    @Column(unique = true)
    @CsvBindByName
    private String login;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private float salary;

    @CsvCustomBindByName(converter=LocalDateConverter.class)
    private LocalDate startDate;
}
