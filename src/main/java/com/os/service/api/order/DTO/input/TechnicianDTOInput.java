package com.os.service.api.order.DTO.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TechnicianDTOInput {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private boolean admin;
    private String pathAssinatura;
}
