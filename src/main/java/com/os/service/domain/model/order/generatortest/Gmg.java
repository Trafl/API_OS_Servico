package com.os.service.domain.model.order.generatortest;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Gmg {

    private String gmg_tFaseR;

    private String gmg_tFaseS;

    private String gmg_tFaseT;
}
