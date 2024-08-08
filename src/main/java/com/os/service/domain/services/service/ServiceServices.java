package com.os.service.domain.services.service;

import com.os.service.domain.model.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceServices {

     Page<Service> getAllServices(Pageable pageable);

     Page<Service> getServicesByGroupId(Pageable pageable, Long groupId);

     Service getServiceById(Long id);

     Service addService(Service service);

     Service updateService(Long id , Service service);

     void deleteServiceById(Long id);
}
