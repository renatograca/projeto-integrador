package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sector")
public class Sector {
    //TODO add nullable = false em warehouseId

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Warehouse warehouse;

    @Column(nullable = false)
    private Integer capacity;

    @OneToOne
    private Supervisor supervisor;

    @Enumerated(EnumType.STRING)
    private Category category;

}
