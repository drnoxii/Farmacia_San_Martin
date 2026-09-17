package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Dto.UsuarioDTO;
import com.sanmartin.farmacias.Entity.Rol;
import com.sanmartin.farmacias.Exception.ResourceNotFoundException;
import com.sanmartin.farmacias.Services.IUsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/usuario"})
@Tag(name = "Usuario", description = "CRUD del Usuario")
public class UsuarioController {
    private final IUsuarioServices usuarioServices;

    public UsuarioController(IUsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @GetMapping
    @Operation(summary = "Lista todos los Usuarios que haya")
    public ResponseEntity<List<UsuarioDTO>> listarTodo() {
        return ResponseEntity.ok(this.usuarioServices.listarUsuarios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Se busca un usuario con un ID especifico")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        return (ResponseEntity) this.usuarioServices.buscarUsuarioPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo encontrar el usuario con el ID: " + id));
    }

    @GetMapping("/correo/{correo}")
    @Operation(summary = "Se busca un usuario con un correo especifico")
    public ResponseEntity<UsuarioDTO> obtenerPorCorreo(@PathVariable String correo) {
        return usuarioServices.findByCorreo(correo).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se obtener el usuario con el siguiente correo: " + correo));
    }

    @GetMapping("/dni/{dni}")
    @Operation(summary = "Se busca un usuario con un DNI especifico")
    public ResponseEntity<UsuarioDTO> obtenerPorDni(@PathVariable String dni) {
        return usuarioServices.findByPersonaDni(dni).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("No se pudo hallar el usuario con el DNI: " + dni));
    }

    @GetMapping("/rol/{rol}")
    @Operation(summary = "Se busca los usuarios con un Rol especifico")
    @Parameter(description = "Rol del usuario: ADMIN, FARMACEUTICO, CAJERO, VENDEDOR o ALMACENERO")
    public ResponseEntity<List<UsuarioDTO>> obtenerPorRol(@PathVariable Rol rol){
        return ResponseEntity.ok(this.usuarioServices.findByRol(rol));
    }

    @PostMapping
    @Operation(summary = "Se crea el Usuario")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody @Valid UsuarioDTO dto){
        return new ResponseEntity(this.usuarioServices.registrarUsuario(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza el usuario con ID especifico")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto){
        return (ResponseEntity) this.usuarioServices.actualizarUsuario(id,dto).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("NO se pudo actualizar el Usuario con ID: "+id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina el usuario con el ID dado")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        if (this.usuarioServices.eliminarUsuario(id)){
            return ResponseEntity.noContent().build();
        }else {
            throw new ResourceNotFoundException("NO se pudo eliminar el Usuario con ID: "+id);
        }
    }
}
