package com.os.service.api.order.DTO.endereco;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTOInput {

    @NotBlank
    private String rua;

    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;
}
