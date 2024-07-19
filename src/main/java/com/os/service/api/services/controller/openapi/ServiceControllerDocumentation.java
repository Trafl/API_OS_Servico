package com.os.service.api.services.controller.openapi;

import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.services.service.ServiceServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Tag(name = "Serviços", description = "Controlador da classe Service")
public interface ServiceControllerDocumentation {

    @Operation(summary = "Lista os serviços e devolve paginado",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    public ResponseEntity<PagedModel<EntityModel<ServiceDTOOutput>>> getAllServices(Pageable pageable, HttpServletRequest request);

    @Operation(summary = "Pega um serviço por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Serviço  não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<ServiceDTOOutput> getOneServiceById(Long serviceId, HttpServletRequest request);

    @Operation(summary = "Atualizar um serviço por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Serviço não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
    public ResponseEntity<ServiceDTOOutput> updateOneServiceById(Long serviceId, ServiceDTOInput newService, HttpServletRequest request);

    @Operation(summary = "Deleta um serviço por Id",
            responses = {
                    @ApiResponse(responseCode = "204"),

                    @ApiResponse(responseCode = "404", description = "Serviço não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<Void> deleteServiceById( Long serviceId, HttpServletRequest request);
}
