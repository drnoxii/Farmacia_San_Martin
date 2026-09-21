package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Dto.ProveedorDto;
import com.sanmartin.farmacias.Services.IProveedorServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "No encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflicto")
})
public class ProveedorController {

    private final IProveedorServices service;

    public ProveedorController(IProveedorServices service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear proveedor")
    @ApiResponse(responseCode = "201", description = "Creado")
    public ResponseEntity<ProveedorDto> crear(
            @Valid @RequestBody ProveedorDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor")
    public ResponseEntity<ProveedorDto> obtener(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar proveedores")
    public ResponseEntity<List<ProveedorDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor")
    public ResponseEntity<ProveedorDto> actualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProveedorDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desactivar proveedor")
    @ApiResponse(responseCode = "204", description = "Desactivado")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Long id
    ) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}