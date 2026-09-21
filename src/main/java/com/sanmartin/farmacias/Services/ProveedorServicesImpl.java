package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.ProveedorDto;
import com.sanmartin.farmacias.Entity.EstadoGeneral;
import com.sanmartin.farmacias.Entity.Proveedor;
import com.sanmartin.farmacias.Entity.Enums.EstadoGeneral;
import com.sanmartin.farmacias.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sanmartin.farmacias.Services.Soporte.*;

@Service
@Transactional(readOnly = true)
public class ProveedorServicesImpl implements IProveedorServices {

    private final ProveedorRepository repo;

    public ProveedorServicesImpl(ProveedorRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public ProveedorDto crear(ProveedorDto dto) {
        duplicado(
                repo.existsByRuc(dto.ruc()),
                "El RUC ya está registrado"
        );

        Proveedor proveedor = new Proveedor();
        aplicar(proveedor, dto);
        proveedor.setEstadoGeneral(EstadoGeneral.ACTIVO);

        return Convertidor.dto(repo.save(proveedor));
    }

    @Override
    public ProveedorDto obtenerPorId(Long id) {
        return Convertidor.dto(obtenerPorId(repo, id, "Proveedor"));
    }

    @Override
    public List<ProveedorDto> listar() {
        return repo.findAll().stream().map(Convertidor::dto).toList();
    }

    @Override
    @Transactional
    public ProveedorDto actualizar(Long id, ProveedorDto dto) {
        Proveedor proveedor = obtenerPorId(repo, id, "Proveedor");

        duplicado(
                repo.existsByRucAndIdProveedorNot(dto.ruc(), id),
                "Otro proveedor ya utiliza ese RUC"
        );

        aplicar(proveedor, dto);

        return Convertidor.dto(repo.save(proveedor));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Proveedor proveedor = obtenerPorId(repo, id, "Proveedor");
        proveedor.setEstadoGeneral(EstadoGeneral.INACTIVO);
    }

    private void aplicar(Proveedor proveedor, ProveedorDto dto) {
        proveedor.setRuc(dto.ruc());
        proveedor.setTelefono(dto.telefono());
        proveedor.setNombre(dto.correo());
        proveedor.setDireccion(dto.direccion());
    }
}