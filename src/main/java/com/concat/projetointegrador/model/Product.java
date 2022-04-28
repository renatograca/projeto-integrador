package com.concat.projetointegrador.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Size(min = 3, max = 30, message = "o nome deve ter entre 3 à 30 caracteres")
    private String name;

    @NotNull(message = "o volume não pode ser nulo")
    private Integer volume;

    @NotNull(message = "o preço não pode ser nulo")
    private BigDecimal price;

    @NotNull(message = "a categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Seller seller;

}
