package com.os.service.domain.services.order;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Order getOneOrderById(Long orderId);

    Order addOrder(Order order);

    Order addServiceToOrder(Long orderId, ServiceInOrder serviceInOrder);

    void starOrder(Long orderId);

    void closeOrder(Long orderId, String generalObservations);

    void cancelOrder(Long orderId);

    public void addGeneratorTestToOrder(Long orderId, GeneratorTest generatorTest);

    public void addGeneratorStatusToOrder(Long orderId, GeneratorStatus generatorStatus );

    public void addGeneralObservationsToOrder(Long orderId, String generalObservations );

    Order updateOrderById(Long orderId);

    void deleteOrderById(Long orderId);

}
