package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Entity
@Table(name = "supervisor")
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "'name' não pode ser nulo")
    private String name;
    @NotNull(message = "'lastname' não pode ser nulo")
    private String lastname;
}
