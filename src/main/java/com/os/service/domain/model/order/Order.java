package com.os.service.domain.model.order;

import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
    private WorkType type;

    private LocalDateTime scheduledDate;

    private Long client_id;

    private Long technician_id;

    private String escopoDosServicos;

    @Enumerated(EnumType.STRING)
    private WorkStatus status = WorkStatus.ABERTO;

    //private Andress

    //--------------------------------------------------------------------

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServiceInOrder> servicesInOrder;
    //Mudar de List para Set
    @Embedded
    private WorkData workData;

    @Embedded
    private GeneratorTest generatorTest;

    @Embedded
    private GeneratorStatus generatorStatus;

    private String generalObservations;

    public void starOrder() {
        this.workData.startOrder();
        this.status = WorkStatus.ANDAMENTO;
    }

    public void closeOrder() {
        this.workData.finishOrder();
        this.status = WorkStatus.FINALIZADO;
    }

    public void cancelOrder() {
        this.status = WorkStatus.CANCELADO;
    }

}
