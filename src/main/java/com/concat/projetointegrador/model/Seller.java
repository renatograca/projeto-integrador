package com.concat.projetointegrador.model;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("Seller")
public class Seller extends User {

    @Builder
    public Seller(Long id, String username, String password, String name, String lastName) {
        super(id,username,password,name,lastName);
    }

}
