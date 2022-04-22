package com.concat.projetointegrador.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(name = "supervisor")
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
}
