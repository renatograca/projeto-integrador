package com.concat.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "a quantidade não pode ser nula")
    private Integer quantity;

    @ManyToOne
    private Product products;// corrigir esse nome

    @ManyToOne
    @JsonIgnore
    private PurchasedOrder purchasedOrder;
}
