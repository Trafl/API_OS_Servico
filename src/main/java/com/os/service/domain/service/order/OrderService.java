package com.os.service.domain.service.order;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.ServiceInOrder;
import com.os.service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Order getOneOrderById(Long orderId);

    Order addOrder(Order order);

    Order addServiceToOrder(ServiceInOrder serviceInOrder);

    void starOrder(Long orderId);

    void closeOrder(Long orderId);

    void cancelOrder(Long orderId);

    Order updateOrderById(Long orderId);

    void deleteOrderById(Long orderId);

}
