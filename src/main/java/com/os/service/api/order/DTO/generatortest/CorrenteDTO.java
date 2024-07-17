package com.os.service.api.order.DTO.generatortest;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CorrenteDTO {

    private String corrente_tFaseR;

    private String corrente_tFaseS;

    private String corrente_tFaseT;

    private String corrente_tBateriaCL;

    private String corrente_tBateriaCD;

    private String corrente_tBateriaIP;

    private String corrente_tBateriaEC;

    private String corrente_frequencia;

    private String corrente_potencia;

    private String corrente_potenciaR;

    private String corrente_potenciaE;

    private String corrente_fatorPotencia;

    private String corrente_temperatura;

    private String corrente_pressaOleo;

    private String corrente_consumoCombustivel;

    private String corrente_temperaturaAd;

    private String corrente_preAquecimento;
}

