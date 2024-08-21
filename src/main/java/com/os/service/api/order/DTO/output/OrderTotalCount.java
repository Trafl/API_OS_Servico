package com.os.service.api.order.DTO.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderTotalCount {

    private Long abertas;
    private Long total;
}
