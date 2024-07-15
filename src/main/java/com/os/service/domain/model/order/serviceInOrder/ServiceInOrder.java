package com.os.service.domain.model.order.serviceInOrder;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.Verification;
import com.os.service.domain.model.service.Service;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_in_order")
public class ServiceInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Enumerated(EnumType.STRING)
    private Verification verificationBefore;

    private String urlPhotoBefore;

    @Enumerated(EnumType.STRING)
    private Verification verificationAfter;

    private String urlPhotoAfter;

    private String observation;

}
