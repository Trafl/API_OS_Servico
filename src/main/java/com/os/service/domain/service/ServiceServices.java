package com.os.service.domain.service;

import com.os.service.domain.model.Services;

import java.util.List;
public interface ServiceServices {

    public List<Services> getAllServices();

    public Services getServiceById(Long id);

    public Services addService(Services services);

    public Services updateService(Long id ,Services services);

    public void deleteServiceById(Long id);
}
