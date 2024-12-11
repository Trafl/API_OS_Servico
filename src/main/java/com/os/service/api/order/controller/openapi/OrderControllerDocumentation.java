package com.os.service.api.order.controller.openapi;


import com.os.service.api.order.DTO.generatorstatus.GeneratorStatusDTO;
import com.os.service.api.order.DTO.generatortest.GeneratorTestDTO;
import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.order.DTO.input.OrderOnePdfDTOInput;
import com.os.service.api.order.DTO.output.OrderAllDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.api.order.DTO.output.OrderOnePdfDTOOutput;
import com.os.service.api.order.DTO.output.OrderTotalCount;
import com.os.service.api.serviceInOrder.DTO.input.ServiceInOrderDTOInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Tag(name = "Ordens de Serviço", description = "Controlador da classe Order")
public interface OrderControllerDocumentation {

    @Operation(summary = "Lista as Ordens de serviço", description = "lista as ordens e devolve paginadas.")
     ResponseEntity<PagedModel<EntityModel<OrderAllDTOOutput>>> getAllOrders(Pageable pageable, HttpServletRequest request);

    @Operation(summary = "Pega uma ordem pelo Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<OrderOneDTOOutput> getOneOrder(Long orderId, HttpServletRequest request);

    @Operation(summary = "Conta quantas ordens em aberto tem no Db",
            responses = {
                    @ApiResponse(responseCode = "200")
            })
    ResponseEntity<OrderTotalCount> getCountOrders(HttpServletRequest request);

    @Operation(summary = "Pega uma ordem entre as datas informadas",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
     ResponseEntity<PagedModel<EntityModel<OrderAllDTOOutput>>> getOrderByDate(LocalDate startDay, LocalDate endDay, Pageable pageable, HttpServletRequest request);

    @Operation(summary = "Adiciona ordem de serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<OrderOneDTOOutput> addOrder(OrderDTOInput dtoInput, HttpServletRequest request);

    @Operation(summary = "Inicia uma Ordem", description = "Inicia uma Orderm de serviço que esta no Db, o tempo do serviço começa a marcar",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> startOrder(Long orderId, HttpServletRequest request);

    @Operation(summary = "Finaliza uma Ordem", description = "Finaliza uma Orderm de serviço que esta no Db, o tempo do serviço para de marcar com a opção de adicionar uma observação geral ou não",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> finishOrder(Long orderId, OrderOnePdfDTOInput orderDTOInput, HttpServletRequest request);

    @Operation(summary = "Cancela uma Ordem", description = "Cancela uma Orderm de serviço que esta no Db",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
     ResponseEntity<Void> cancelOrder( Long orderId, HttpServletRequest request);

    @Operation(summary = "Adiciona um Serviço a Ordem", description = "Adiciona um serviço a Ordem de serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
     ResponseEntity<OrderOneDTOOutput> addServiceToOrder(Long orderId, ServiceInOrderDTOInput dtoInput, HttpServletRequest request);

    @Operation(summary = "Adiciona o Teste do Gerador a Ordem de serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
     ResponseEntity<Void> addGeneratorTestToOrder(Long orderId, GeneratorTestDTO dtoInput, HttpServletRequest request);

    @Operation(summary = "Adiciona o Status do Gerador a Ordem de serviço",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
     ResponseEntity<Void> addGeneratorStatusToOrder(Long orderId, GeneratorStatusDTO dtoInput, HttpServletRequest request);

    @Operation(summary = "Pega o Modelo para PDF de uma Ordem por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
     ResponseEntity<OrderOnePdfDTOOutput> getOneOrderForPDF(Long orderId, HttpServletRequest request);


    @Operation(summary = "Salva a assinatura do cliente",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Ordem não foi encontrada no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<Void> addClientSignature( Long orderId,MultipartFile image, HttpServletRequest request);
}
