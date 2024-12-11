package com.os.service.api.order.DTO.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTOInput {

        private String rua;
        private String bairro;
        private String cidade;
        private String numero;
}
