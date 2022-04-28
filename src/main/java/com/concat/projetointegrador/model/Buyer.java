package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull(message = "lastName não pode ser nulo")
    @Size(min = 1, max = 20, message = "lastName deve possuir de 1 a 20 caracteres")
    private String lastName;

    @NotNull(message = "cpf não pode ser nulo")
    @Size(min = 11, max = 11, message = "cpf deve possuir 11 caracteres")
    private Integer cpf;
}
