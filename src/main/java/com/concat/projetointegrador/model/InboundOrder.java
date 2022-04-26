package com.concat.projetointegrador.model;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDate orderDate = LocalDate.now();
    
    private boolean active;

    @ManyToOne
    private Sector sector;

    @OneToMany(mappedBy = "inboundOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BatchStock> batchStock;
}
