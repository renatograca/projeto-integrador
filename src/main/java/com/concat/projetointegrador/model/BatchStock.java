package com.concat.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "a categoria não pode ser vazia")
    @NotNull(message = "a categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotEmpty(message = "a quantidade inicial não pode ser vazia")
    @NotNull(message = "a quantidade inicial não pode ser nula")
    private Integer initialQuantity;

    @NotEmpty(message = "a quantidade atual não pode ser vazia")
    @NotNull(message = "a quantidade atual não pode ser nula")
    private Integer currentQuantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "a data de fabricação não pode ser vazia")
    @NotNull(message = "a data de fabricação não pode ser nula")
    private LocalDate manufacturingDate;

    @NotEmpty(message = "o horário de fabricação não pode ser vazio")
    @NotNull(message = "o horário de fabricação não pode ser nulo")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime manufacturingTime;

    @NotEmpty(message = "a validade do produto não pode ser vazia")
    @NotNull(message = "a validade do produto não pode ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @OneToOne
    private Product product;

    @ManyToOne
    @JsonIgnore
    private InboundOrder inboundOrder;
}
