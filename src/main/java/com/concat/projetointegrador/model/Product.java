package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "o nome não pode ser nulo")
    @NotEmpty (message = "o nome não pode ser vazio")
    @Size(min = 3, max = 30, message = "o nome deve ter entre 3 à 30 caracteres")
    private String name;

    @NotEmpty(message = "o volume não pode ser vazio")
    @NotNull(message = "o volume não pode ser nulo")
    private Integer volume;

    @NotEmpty(message = "o preço não pode ser vazio")
    @NotNull(message = "o preço não pode ser nulo")
    private BigDecimal price;

    @NotEmpty(message = "a categoria não pode ser vazia")
    @NotNull(message = "a categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Seller seller;

}
