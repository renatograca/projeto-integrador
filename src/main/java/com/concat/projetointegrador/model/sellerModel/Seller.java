package com.concat.projetointegrador.model.sellerModel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Setter @Getter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
   @Pattern(regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$", message = "nome deve ser composto de letras")
    @NotNull(message = "name não pode ser null") @Size(min = 1, max = 20, message = "name deve possuir de 1 a 20 caracteres.")
    private String name;
    @NotNull(message = "lastName não pode ser null") @Size(min = 1, max = 20, message = "LastName deve possuir de 1 a 20 caracteres.")
    private String lastName;

}
