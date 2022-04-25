package com.concat.projetointegrador.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "TB_SECTOR")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long warehouseId;
    @Column(nullable = false)
    private Integer capacity;
    private Boolean active = true;

}
