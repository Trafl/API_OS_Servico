package com.os.service.domain.services.order;

import com.os.service.api.order.DTO.output.OrderTotalCount;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


public interface OrderService {

    Page<Order> getAllOrders(Pageable pageable);

    Order getOneOrderById(Long orderId);

    Page<Order> GetOrderByDate(Pageable pageable, LocalDateTime starDay, LocalDateTime endDay);

    OrderTotalCount countOrder();

    Order addOrder(Order order);

    Order addServiceToOrder(Long orderId, ServiceInOrder serviceInOrder);

    void starOrder(Long orderId);

    void closeOrder(Long orderId, String generalObservations);

    void cancelOrder(Long orderId);

    void addGeneratorTestToOrder(Long orderId, GeneratorTest generatorTest);

    void addGeneratorStatusToOrder(Long orderId, GeneratorStatus generatorStatus);

    void deleteOrderById(Long orderId);

    void addClientSignature(Long orderId, MultipartFile image);

}
