package com.os.service.domain.service;

import com.os.service.domain.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ServiceServices {

    public Page<Service> getAllServices(Pageable pageable);

    public Service getServiceById(Long id);

    public Service addService(Service service);

    public Service updateService(Long id , Service service);

    public void deleteServiceById(Long id);
}
