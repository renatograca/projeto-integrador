package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDate date = LocalDate.now();

    @NotNull(message = "o status não pode ser nulo")
    private String status;
    @ManyToOne
    private Buyer buyer;
    @OneToMany(mappedBy = "purchasedOrder")
    private List<Cart> cart;
}
