package com.os.service.domain.services.serviceInOrder;

import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceInOrderService {

     ServiceInOrder getServiceById(Long id);

     ServiceInOrder saveServiceInOrder(ServiceInOrder serviceInOrder);

     void deleteServiceInOrderById(Long id);

     void addPhotoBefore(Long id, MultipartFile file);

     void addPhotoAfter(Long id, MultipartFile file);
}
