package com.os.service.domain.repository;

import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceInOrderRepository extends JpaRepository<ServiceInOrder, Long> {
}
