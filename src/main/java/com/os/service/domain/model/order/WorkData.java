package com.os.service.domain.model.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.ZoneId;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class WorkData {

    private LocalTime start;

    private LocalTime end;

    public void startOrder(){
        this.start = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public void finishOrder(){
        this.end = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public void cancelOrder(){
        if(start != null){
            this.end = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        }else{
            this.start = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
            this.end = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        }

    }
}
