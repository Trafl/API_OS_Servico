package com.os.service.domain.model.order;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {

    private String rua;

    private Integer numero;

    private String bairro;

    private String cidade;
}
