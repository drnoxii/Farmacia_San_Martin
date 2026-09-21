package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.ProveedorDto;
<<<<<<< Updated upstream
import org.springframework.stereotype.Service;

=======
>>>>>>> Stashed changes
import java.util.List;

@Service
public interface IProveedorServices {

    ProveedorDto crear(ProveedorDto dto);

    ProveedorDto obtenerPorId(Long id);

    List<ProveedorDto> listar();

    ProveedorDto actualizar(Long id, ProveedorDto dto);

    void eliminar(Long id);
}