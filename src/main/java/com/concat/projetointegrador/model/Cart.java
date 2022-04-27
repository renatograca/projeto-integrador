package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private Product products;

    @ManyToOne
    private PurchasedOrder purchasedOrder;
}
