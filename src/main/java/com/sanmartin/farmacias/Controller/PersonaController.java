package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Dto.PersonaDto;
import com.sanmartin.farmacias.Exception.ResourceNotFoundException;
import com.sanmartin.farmacias.Services.IPersonaServices;
import com.sanmartin.farmacias.Services.PersonaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@Tag(name = "Personas", description = "CRUD de personas del sistema")
public class PersonaController {

    private final PersonaServiceImpl personaService;

    public PersonaController(PersonaServiceImpl personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    @Operation(summary = "Crear persona")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Persona creada"),
            @ApiResponse(responseCode = "400", description = "DNI duplicado o datos inválidos")
    })
    public ResponseEntity<PersonaDto> crear(@Valid @RequestBody PersonaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personaService.crear(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener persona por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona encontrada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    public ResponseEntity<PersonaDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas las personas")
    public ResponseEntity<List<PersonaDto>> listar() {
        return ResponseEntity.ok(personaService.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar persona")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona actualizada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
            @ApiResponse(responseCode = "400", description = "DNI duplicado o datos inválidos")
    })
    public ResponseEntity<PersonaDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PersonaDto dto) {
        return ResponseEntity.ok(personaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar persona")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Persona eliminada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
            @ApiResponse(responseCode = "400", description = "La persona tiene un usuario asociado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}