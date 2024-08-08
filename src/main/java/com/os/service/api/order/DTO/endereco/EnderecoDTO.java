package com.os.service.api.order.DTO.endereco;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

    @NotBlank
    private String rua;

    @NotNull
    private Integer numero;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;
}
