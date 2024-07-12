package com.os.service.domain.model.order;

import com.os.service.domain.model.service.Service;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scheduledDate;

    @Embedded
    private Adress adress;

    @Embedded
    private ClientData clientData;

    @Embedded
    private EquipmentData equipmentData;

    @Embedded
    private WorkData workData;

    @Enumerated(EnumType.STRING)
    private WorkType type;

    @Enumerated(EnumType.STRING)
    private WorkStatus status = WorkStatus.ABERTO;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServiceInOrder> servicesInOrder;

    private String generalObservations;

    public void starOrder() {
        this.status = WorkStatus.ANDAMENTO;
    }

    public void closeOrder() {
        this.status = WorkStatus.FINALIZADO;
    }

    public void cancelOrder() {
        this.status = WorkStatus.CANCELADO;
    }

    // assinatura tecnico

    // assinatura cliente
}
