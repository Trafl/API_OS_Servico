package com.os.service.api.groupServices.DTO;

import com.os.service.api.services.DTO.ServiceDTOOutput;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTOInput{
    @NotBlank
    String name;
}
