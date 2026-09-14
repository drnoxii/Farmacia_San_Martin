package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Dto.*;
import com.sanmartin.farmacias.Services.CajaServicesImpl;
import com.sanmartin.farmacias.Services.ICajaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cajas")
@Tag(name = "Cajas", description = "Apertura, cierre y movimientos de caja")
public class CajaController {

    private final ICajaServices cajaService;

    public CajaController(ICajaServices cajaService) {
        this.cajaService = cajaService;
    }

    // ==========================================
    // APERTURA
    // ==========================================
    @PostMapping("/abrir")
    @Operation(summary = "Abrir una caja para un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Caja abierta correctamente"),
            @ApiResponse(responseCode = "400", description = "El usuario ya tiene una caja abierta o datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<CajaResponseDTO> abrirCaja(
            @Valid @RequestBody CajaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cajaService.abrirCaja(dto));
    }

    // ==========================================
    // CIERRE
    // ==========================================
    @PostMapping("/{idCaja}/cerrar")
    @Operation(summary = "Cerrar caja indicando el monto contado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caja cerrada correctamente"),
            @ApiResponse(responseCode = "400", description = "La caja ya está cerrada o datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Caja no encontrada")
    })
    public ResponseEntity<CajaResponseDTO> cerrarCaja(
            @PathVariable Long idCaja,
            @Valid @RequestBody CajaCierreRequestDTO dto) {
        return ResponseEntity.ok(cajaService.cerrarCaja(idCaja, dto));
    }

    // ==========================================
    // CONSULTAS DE CAJA
    // ==========================================
    @GetMapping("/{idCaja}")
    @Operation(summary = "Obtener caja por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caja encontrada"),
            @ApiResponse(responseCode = "404", description = "Caja no encontrada")
    })
    public ResponseEntity<CajaResponseDTO> obtener(@PathVariable Long idCaja) {
        return ResponseEntity.ok(cajaService.obtenerPorId(idCaja));
    }

    @GetMapping
    @Operation(summary = "Listar todas las cajas")
    public ResponseEntity<List<CajaResponseDTO>> listar() {
        return ResponseEntity.ok(cajaService.listar());
    }

    @GetMapping("/abiertas")
    @Operation(summary = "Listar cajas abiertas actualmente")
    public ResponseEntity<List<CajaResponseDTO>> listarAbiertas() {
        return ResponseEntity.ok(cajaService.listarAbiertas());
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Listar cajas de un usuario")
    public ResponseEntity<List<CajaResponseDTO>> listarPorUsuario(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(cajaService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/rango")
    @Operation(summary = "Listar cajas por rango de fechas de apertura")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de cajas"),
            @ApiResponse(responseCode = "400", description = "Formato de fecha inválido")
    })
    public ResponseEntity<List<CajaResponseDTO>> listarPorRango(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime hasta) {
        return ResponseEntity.ok(cajaService.listarPorRangoFechas(desde, hasta));
    }

    // ==========================================
    // MOVIMIENTOS
    // ==========================================
    @PostMapping("/movimientos")
    @Operation(summary = "Registrar un movimiento de caja (ingreso, egreso o ajuste)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Movimiento registrado"),
            @ApiResponse(responseCode = "400", description = "La caja está cerrada o datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Caja o usuario no encontrados")
    })
    public ResponseEntity<MovimientoCajaResponseDTO> registrarMovimiento(
            @Valid @RequestBody MovimientoCajaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cajaService.registrarMovimiento(dto));
    }

    @GetMapping("/{idCaja}/movimientos")
    @Operation(summary = "Listar movimientos de una caja")
    public ResponseEntity<List<MovimientoCajaResponseDTO>> listarMovimientos(
            @PathVariable Long idCaja) {
        return ResponseEntity.ok(cajaService.listarMovimientos(idCaja));
    }
}
