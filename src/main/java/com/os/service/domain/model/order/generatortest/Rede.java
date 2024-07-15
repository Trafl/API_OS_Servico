package com.os.service.domain.model.order.generatortest;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Rede {

    private String rede_tFaseR;

    private String rede_tFaseS;

    private String rede_tFaseT;
}
