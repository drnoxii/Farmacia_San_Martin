package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.LaboratorioDto;

import java.util.List;
import java.util.Optional;

public interface ILaboratorioServices {

    List<LaboratorioDto> listarTodo();

    Optional<LaboratorioDto> buscarPorId(Long id);

    LaboratorioDto registrar(LaboratorioDto laboratorioDto);

    Optional<LaboratorioDto> actualizar(
            Long id,
            LaboratorioDto laboratorioDto
    );

    boolean eliminar(Long id);
}