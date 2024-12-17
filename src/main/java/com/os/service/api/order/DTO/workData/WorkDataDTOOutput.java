package com.os.service.api.order.DTO.workData;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class WorkDataDTOOutput {

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime start;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime end;

}
