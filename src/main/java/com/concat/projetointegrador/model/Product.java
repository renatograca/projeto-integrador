package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private Float currentTemperature;
    private Float initialTemperature;
    private Integer initialQuality;
    private Integer initialCurrent;
    private LocalDate manuFacturingDate;
    private LocalDate manuFacturingTime;
    private LocalDate dueDate;
    private Integer size;
    private Category category;


}
