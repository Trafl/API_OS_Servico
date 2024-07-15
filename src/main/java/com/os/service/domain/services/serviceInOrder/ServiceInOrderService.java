package com.os.service.domain.services.serviceInOrder;

import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;

public interface ServiceInOrderService {

    public ServiceInOrder getServiceById(Long id);

    public ServiceInOrder saveServiceInOrder(ServiceInOrder serviceInOrder);

    public void deleteServiceInOrderById(Long id);
}
