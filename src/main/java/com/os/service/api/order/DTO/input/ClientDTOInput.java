package com.os.service.api.order.DTO.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTOInput {

        private Long id;

        private String nome;

        private String cpf;

        private String cnpj;

        private String email;

        private String telefone;

        private EnderecoDTOInput endereco;

        private String tipo;

        private String nomeContato;

        private String tipoContrato;

        private boolean possuiContrato;

    }
