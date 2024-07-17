package com.os.service.api.order.DTO.generatortest;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class RedeDTO {

    private String rede_tFaseR;

    private String rede_tFaseS;

    private String rede_tFaseT;
}
