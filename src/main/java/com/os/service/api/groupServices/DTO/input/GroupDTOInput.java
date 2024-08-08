package com.os.service.api.groupServices.DTO.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTOInput{
    @NotBlank
    String name;
}
