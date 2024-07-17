package com.os.service.domain.services.serviceInOrder;

import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceInOrderService {

    public ServiceInOrder getServiceById(Long id);

    public ServiceInOrder saveServiceInOrder(ServiceInOrder serviceInOrder);

    public void deleteServiceInOrderById(Long id);

    public void addPhotoBefore(Long id, MultipartFile file);

    public void addPhotoAfter(Long id, MultipartFile file);
}
