package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
@NoArgsConstructor
@DiscriminatorValue("Buyer")
public class Buyer extends User {

    @Builder
    public Buyer(Long id, String username, String password,  String name, String lastName, Long cpf) {
        super(id,username,password,name,lastName, cpf);
    }
}
