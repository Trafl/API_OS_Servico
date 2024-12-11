package com.os.service.api.order.DTO.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipamentoDTOInput {

    @JsonProperty("KWH")
    private Float KWH;
    private String motor;
    private String numero;
    private String tensao;
    private String corrente;
    private String potencia;

    @JsonProperty("horimetro")
    private Float horimetro;

    private String alternador;
    private String fabricante;
    private String frequencia;
    private String uscaModelo;
    private String modeloMotor;
    private String numeroMotor;
    private String anoFabricacao;
    private String fatorPotencia;
    private String painelControle;
    private String tipoEquipamento;
    private String modeloAlternador;
    private String numeroAlternador;
    private String potenciaEletrica;
}
