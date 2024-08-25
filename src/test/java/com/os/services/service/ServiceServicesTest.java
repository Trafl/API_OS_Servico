package com.os.services.service;

import com.os.service.domain.exception.ServiceNotFoundException;
import com.os.service.domain.model.service.Service;
import com.os.service.domain.repository.ServicesRepository;
import com.os.service.domain.services.service.ServiceServicesImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServiceServicesTest {

    @InjectMocks
    private ServiceServicesImpl servicesImp;
    @Mock
    private ServicesRepository repository;

    private Service service;

    @BeforeEach
    void setUp(){
        service = new Service("Service1", "Description1");
    }

    @Nested
    class findById{

        @Test
        public void given_id_when_GetById_Then_Return_OneService(){
            service.setId(1L);

            given(repository.findById(anyLong())).willReturn(Optional.of(service));

            var result = servicesImp.getServiceById(anyLong());

            assertTrue(result instanceof  Service);
            assertNotNull(result);
            assertEquals(service.getId(), result.getId());
            assertEquals(service.getName(), result.getName());
            assertEquals(service.getDescription(), result.getDescription());

        }

        @Test
        public void test_throws_exception_when_service_id_not_exists() {

            given(repository.findById(anyLong())).willReturn(Optional.empty());

            var result = assertThrows(ServiceNotFoundException.class, () -> {
                servicesImp.getServiceById(1L);

            });
            assertEquals("Service with Id: 1 not found in database.", result.getMessage());
        }
    }

    @Nested
    class getServicesByGroupId{
        @Test
        public void test_retrieve_services_by_valid_group_id() {
            Pageable pageable = PageRequest.of(0, 10);

            List<Service> serviceList = Arrays.asList(service, new Service("Service2", "Description2"));
            Page<Service> servicePage = new PageImpl<>(serviceList);

            given(repository.findServicesByGroupId(pageable, 1L)).willReturn(servicePage);

            var result = servicesImp.getServicesByGroupId(pageable, 1L);

            assertNotNull(result);
            assertEquals(serviceList.size(), result.getTotalElements());
        }

        @Test
        public void test_handle_non_existent_group_id() {
            Pageable pageable = PageRequest.of(0, 10);
            Long groupId = 999L;
            Page<Service> emptyPage = Page.empty();


            given(repository.findServicesByGroupId(pageable, groupId)).willReturn(emptyPage);

            Page<Service> result = servicesImp.getServicesByGroupId(pageable, groupId);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(0, result.getTotalElements());
        }

    }

    @Nested
    class addService {

        @Test
        public void test_add_service_success() {
            service.setId(1L);
            given(repository.save(service)).willReturn(service);

            var savedService = servicesImp.addService(service);

            assertNotNull(savedService);
            assertEquals(service.getId(), savedService.getId());
            assertEquals(service.getName(), savedService.getName());
            assertEquals(service.getDescription(), savedService.getDescription());
        }
    }

    @Nested
    class updateService {

        @Test
        public void test_update_service_success() {
            service.setId(1L);
            Service updatedService = new Service("New Name", "New Description");

            given(repository.findById(1L)).willReturn(Optional.of(service));
            given(repository.save(any(Service.class))).willReturn(new Service(1L, "New Name", "New Description"));

            var result = servicesImp.updateService(1L, updatedService);

            assertEquals(updatedService.getName(), result.getName());
            assertEquals(updatedService.getDescription(), result.getDescription());
        }

        @Test
        public void test_update_service_id_not_found() {
            Service updatedService = new Service("New Name", "New Description");

            given(repository.findById(anyLong())).willReturn(Optional.empty());

            var result = assertThrows(ServiceNotFoundException.class, () -> {
                servicesImp.updateService(1L, updatedService);
            });

            assertEquals("Service with Id: 1 not found in database.", result.getMessage());
        }
    }

    @Nested
    class deleteServiceById {

        @Test
        public void test_delete_service_with_valid_id() {
            Service mockService = new Service();
            given(repository.findById(anyLong())).willReturn(Optional.of(mockService));

            servicesImp.deleteServiceById(1L);

            verify(repository, times(1)).deleteById(1L);
        }

        @Test
        public void test_delete_service_with_non_existent_id() {
            given(repository.findById(anyLong())).willReturn(Optional.empty());

            var result = assertThrows(ServiceNotFoundException.class, () -> {
                servicesImp.deleteServiceById(1L);
            });
            assertEquals("Service with Id: 1 not found in database.", result.getMessage());
        }
    }
}
