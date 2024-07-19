package com.os.service.api.groupServices.controller.openapi;

import com.os.service.api.groupServices.DTO.input.GroupDTOInput;
import com.os.service.api.groupServices.DTO.output.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.output.GroupDTONameOutput;
import com.os.service.api.groupServices.DTO.output.GroupOneDTOOutput;
import com.os.service.api.groupServices.mapper.GroupMapper;
import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.services.groupservice.GroupSServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Tag(name = "Grupo de Serviços", description = "Controlador da classe GroupService")
public interface GroupServiceControllerDocumentation {


    @Operation(summary = "Lista os grupos de serviço em pagina",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    public ResponseEntity<PagedModel<EntityModel<GroupAllDTOOutput>>> getAllGroups(Pageable pageable, HttpServletRequest request);

    @Operation(summary = "Pega um Grupo pelo Id",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Grupo não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
    public ResponseEntity<GroupOneDTOOutput> getOneGroupById( Long groupId, HttpServletRequest request);

    @Operation(summary = "Adiciona um grupo", description = "Adiciona um Grupo de serviços ao bando de dados",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),
            })
    public ResponseEntity<GroupOneDTOOutput> addGroup(GroupDTOInput dtoInput, HttpServletRequest request);


    @Operation(summary = "Adiciona um serviço ao grupo", description = "Adiciona um serviço ao grupo de serviços ",
            responses = {
                    @ApiResponse(responseCode = "201"),

                    @ApiResponse(responseCode = "404", description = "Grupo não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<GroupOneDTOOutput> addServiceToGroupById(Long groupId, ServiceDTOInput dtoInput, HttpServletRequest request);


    @Operation(summary = "Atualiza  um grupo",
            responses = {
                    @ApiResponse(responseCode = "200"),

                    @ApiResponse(responseCode = "404", description = "Grupo não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<GroupDTONameOutput> updateGroupById(Long groupId,GroupDTOInput dtoInput, HttpServletRequest request);


    @Operation(summary = "Adiciona um serviço ao grupo", description = "Adiciona um serviço ao grupo de serviços ",
            responses = {
                    @ApiResponse(responseCode = "204"),

                    @ApiResponse(responseCode = "404", description = "Grupo não foi encontrado no banco de dados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail"))),

                    @ApiResponse(responseCode = "400", description = "Erro nos campos digitados",
                            content = @Content(schema = @Schema(ref = "ProblemDetail")))
            })
    public ResponseEntity<Void> disableGroupById( Long groupId, HttpServletRequest request);
}
