package com.concat.projetointegrador.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Seller")
public class Seller extends User {

    @Builder
    public Seller(Long id, String username, String password, String name, String lastName) {
        super(id,username,password,name,lastName);
    }

}
