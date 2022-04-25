package com.concat.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "o nome não pode ser nulo")
    @NotEmpty (message = "o nome não pode ser vazio")
    @Size(min = 3, max = 30, message = "o nome deve ter entre 3 à 30 caracteres")
    private String name;
    @NotEmpty(message = "a temperatura atual não pode ser vazia")
    @NotNull(message = "a temperatura atual não pode ser nula")
    private Float currentTemperature;
    @NotEmpty(message = "a temperatura inicial não pode ser vazia")
    @NotNull(message = "a temperatura inicial não pode ser nula")
    private Float initialTemperature;
    @NotEmpty(message = "a qualidade inicial não pode ser vazia")
    @NotNull(message = "a qualidade inicial não pode ser nula")
    private Integer initialQuality;
    @NotEmpty(message = "a qualidade atual não pode ser vazia")
    @NotNull(message = "a qualidade atual não pode ser nula")
    private Integer currentQuality;
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
    @NotEmpty(message = "o volume não pode ser vazio")
    @NotNull(message = "o volume não pode ser nulo")
    private Integer size;
    @NotEmpty(message = "a categoria não pode ser vazia")
    @NotNull(message = "a categoria não pode ser nula")
    @Enumerated(EnumType.STRING)
    private Category category;

}
