package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Dto.PersonaDto;
import com.sanmartin.farmacias.Exception.ResourceNotFoundException;
import com.sanmartin.farmacias.Services.IPersonaServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/persona"})
public class PersonaController {
    private final IPersonaServices PersonaServices;

    public PersonaController(IPersonaServices personaServices) {
        PersonaServices = personaServices;
    }

    @GetMapping
    public ResponseEntity<List<PersonaDto>> listar(){
        return ResponseEntity.ok(this.PersonaServices.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> obtener(@PathVariable Long id){
        return (ResponseEntity)this.PersonaServices.buscarPorId(id).map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("La persona con el ID:"+id+ " no ha sido encontrada "));
    }
    @PostMapping
    public ResponseEntity<PersonaDto> registrar(@RequestBody @Valid PersonaDto dto){
        return new ResponseEntity(this.PersonaServices.registrar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> actualizar(@PathVariable Long id, @Valid PersonaDto dto){
        return (ResponseEntity)this.PersonaServices.actualizar(id, dto).map(ResponseEntity::ok).orElseThrow(()-> new ResourceNotFoundException("No se pudo actualizar el registro con ID: "+id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (this.PersonaServices.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else {
            throw new ResourceNotFoundException("No se pudo eliminar el registro con ID: "+id);
        }
    }
}
