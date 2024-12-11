package com.os.service.api.order.DTO.endereco;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTOOutput {

    private String rua;

    private Integer numero;

    private String bairro;

    private String cidade;
}
