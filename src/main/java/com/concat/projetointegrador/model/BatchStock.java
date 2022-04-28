package com.concat.projetointegrador.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull(message = "a categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "a quantidade inicial não pode ser nula")
    private Integer initialQuantity;

    @NotNull(message = "a quantidade atual não pode ser nula")
    private Integer currentQuantity;

//    @NotNull(message = "a data de fabricação não pode ser nula")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;

    @NotNull(message = "o horário de fabricação não pode ser nulo")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime manufacturingTime;

    @NotNull(message = "a validade do produto não pode ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @OneToOne
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private InboundOrder inboundOrder;
}
