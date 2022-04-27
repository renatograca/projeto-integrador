package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "o nome não pode ser nulo")
    @Pattern(regexp = "^[a-zA-Z]{1,}(?: [a-zA-Z]+){0,2}$", message = "nome deve ser composto de letras")
    private String name;
}
