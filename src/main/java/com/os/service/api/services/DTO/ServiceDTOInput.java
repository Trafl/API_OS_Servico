package com.os.service.api.services.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServiceDTOInput{

        @NotBlank
        private String name;

        @NotBlank
        private String description;
}
