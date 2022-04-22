package com.concat.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorModel {
    @Id
    private String id;
    private String name;
    private String lastname;
}
