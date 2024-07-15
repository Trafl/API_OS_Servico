package com.os.service.services.service;

import com.os.service.domain.exception.ServiceNotFoundException;
import com.os.service.domain.model.service.Service;
import com.os.service.domain.repository.ServicesRepository;
import org.junit.jupiter.api.*;
import com.os.service.domain.services.service.ServiceServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ServiceServiceTest {

    private ServicesRepository repository;
    private ServiceServicesImpl serviceServices;

    @BeforeEach
     void setup(){
        repository = mock(ServicesRepository.class);
        serviceServices = new ServiceServicesImpl(repository);
    }

//    @Nested
//    public class GetAllServicesTest {
//        @Test
//        @DisplayName("Returns a list of all services when the repository contains multiple services")
//        public void test_returns_all_services_when_repository_contains_multiple_services() {
//
//            var service1 = new Service(1L, "Service1", "Description1");
//            var service2 = new Service(2L, "Service2", "Description2");
//            List<Service> serviceList = List.of(service1, service2);
//
//            when(repository.findAll()).thenReturn(serviceList);
//
//            List<Service> result = serviceServices.getAllServices();
//
//            assertNotNull(result);
//            assertEquals(2, result.size());
//            assertEquals("Service1", result.get(0).getName());
//            assertEquals("Service2", result.get(1).getName());
//        }
//
//        @Test
//        @DisplayName("Handles the scenario when the repository is temporarily unavailable")
//        public void test_handles_repository_temporarily_unavailable() {
//
//            when(repository.findAll()).thenThrow(new RuntimeException("Repository temporarily unavailable"));
//
//            Exception exception = assertThrows(RuntimeException.class, () -> {
//                serviceServices.getAllServices();
//            });
//
//            assertEquals("Repository temporarily unavailable", exception.getMessage());
//        }
//
//        @Test
//        @DisplayName("Handles the scenario when the repository throws an unexpected exception")
//        public void test_handles_unexpected_repository_exception() {
//
//            when(repository.findAll()).thenThrow(new RuntimeException("Unexpected exception"));
//
//            Exception exception = assertThrows(RuntimeException.class, () -> {
//                serviceServices.getAllServices();
//            });
//
//            assertEquals("Unexpected exception", exception.getMessage());
//        }
//
//    }

    @Nested
    public class GetServiceByIdTest {

        @Test
        @DisplayName("Successfully retrieves a service when a valid ID is provided")
        public void test_successfully_retrieves_service_with_valid_id() {
            var service = new Service(1L, "Test Service", "Test Description");
            when(repository.findById(1L)).thenReturn(Optional.of(service));

            var result = serviceServices.getServiceById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId().longValue());
            assertEquals("Test Service", result.getName());
            assertEquals("Test Description", result.getDescription());
        }

        @Test
        @DisplayName("Throws ServiceNotFoundException when the service ID does not exist")
        public void test_throws_service_not_found_exception_when_id_does_not_exist() {
            when(repository.findById(1L)).thenReturn(Optional.empty());

           var result = assertThrows(ServiceNotFoundException.class,
                    () -> {serviceServices.getServiceById(1L);
            });

           assertEquals("Service with Id: 1 not found in database.", result.getMessage());
        }
    }

    @Nested
    public class AddServiceTest {

        @Test
        @DisplayName("Successfully saves a valid service object")
        public void test_successfully_saves_valid_service_object() {

            var service = new Service(null, "Test Service", "Test Description");
            var savedService = new Service(1L, "Test Service", "Test Description");

            when(repository.save(service)).thenReturn(savedService);

            var result = serviceServices.addService(service);

            assertNotNull(result);
            assertEquals(savedService.getId(), result.getId());
            assertEquals(savedService.getName(), result.getName());
            assertEquals(savedService.getDescription(), result.getDescription());
        }
    }

    @Nested
    public class UpdateServiceTest {

        @Test
        @DisplayName("Successfully updates an existing service with valid id and data")
        public void test_update_service_successfully() {
            Service existingService = new Service(1L, "Old Name", "Old Description");
            Service updatedService = new Service(1L, "New Name", "New Description");

            when(repository.findById(1L)).thenReturn(Optional.of(existingService));
            when(repository.save(existingService)).thenReturn(updatedService);

            Service result = serviceServices.updateService(1L, updatedService);

            assertEquals("New Name", result.getName());
            assertEquals("New Description", result.getDescription());
        }

        @Test
        @DisplayName("Throws ServiceNotFoundException if service id does not exist")
        public void test_update_service_throws_exception_if_id_not_found() {
            Service updatedService = new Service(1L, "New Name", "New Description");

            when(repository.findById(1L)).thenReturn(Optional.empty());

            var result = assertThrows(ServiceNotFoundException.class, () -> {
                serviceServices.updateService(1L, updatedService);
            });

            assertEquals("Service with Id: 1 not found in database.", result.getMessage());
        }
    }

    @Nested
    public class DeleteServiceByIdTest {

        @Test
        @DisplayName("Successfully deletes an existing service by id")
        public void test_successfully_deletes_existing_service_by_id() {
            Long serviceId = 1L;
            var service = new Service(1L, "Test Name", "Test Description");

            given(repository.findById(anyLong())).willReturn(Optional.of(service));

            doNothing().when(repository).deleteById(serviceId);

            serviceServices.deleteServiceById(serviceId);

            verify(repository, times(1)).deleteById(serviceId);
        }

        @Test
        @DisplayName("Attempt to delete a service with a non-existent id throw ServiceNotFoundException")
        public void test_attempt_to_delete_service_with_non_existent_id() {
            Long nonExistentServiceId = 999L;

           var result = assertThrows(ServiceNotFoundException.class,
                   () -> serviceServices.deleteServiceById(nonExistentServiceId));

           assertEquals("Service with Id: 999 not found in database.", result.getMessage());
        }
    }
}
