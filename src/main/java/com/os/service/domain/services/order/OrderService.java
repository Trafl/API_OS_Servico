package com.os.service.domain.services.order;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Order getOneOrderById(Long orderId);

    Order addOrder(Order order);

    Order addServiceToOrder(Long orderId, ServiceInOrder serviceInOrder);

    void starOrder(Long orderId);

    void closeOrder(Long orderId);

    void cancelOrder(Long orderId);

    Order updateOrderById(Long orderId);

    void deleteOrderById(Long orderId);

}
