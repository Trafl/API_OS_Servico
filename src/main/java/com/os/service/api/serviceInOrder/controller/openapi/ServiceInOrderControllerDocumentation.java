package com.os.service.api.serviceInOrder.controller.openapi;

import com.os.service.api.serviceInOrder.DTO.output.ServiceInOrderDTOOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "Serviços das Ordens", description = "Controlador da classe ServiceInOrder")
public interface ServiceInOrderControllerDocumentation {

    @Operation(summary = "Pega um service in order por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Serviço da ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<ServiceInOrderDTOOutput> getServiceInOrderById(Long serviceInOrderId, HttpServletRequest request);


    @Operation(summary = "Adiciona a foto de antes a um serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Serviço da ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro ao Updar a imagem na amazon",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> addPhotoBeforeToOrder(Long serviceInOrderId,  MultipartFile image, HttpServletRequest request);

    @Operation(summary = "Adiciona a foto de depois a um serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Serviço da ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro ao Updar a imagem na amazon",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> addPhotoAfterToOrder( Long serviceInOrderId, MultipartFile image, HttpServletRequest request);


    @Operation(summary = "Deletar um orderInService",
            responses = {
                    @ApiResponse(responseCode = "204"),

                    @ApiResponse(responseCode = "404", description = "Serviço da ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> deleteServiceInOrder(Long serviceInOrderId, HttpServletRequest request);
}
