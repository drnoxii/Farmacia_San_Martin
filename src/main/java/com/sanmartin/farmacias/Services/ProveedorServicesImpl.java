package com.sanmartin.farmacias.Services;

import com.sanmartin.farmacias.Dto.ProveedorDto;
import com.sanmartin.farmacias.Entity.Proveedor;
import com.sanmartin.farmacias.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicesImpl implements IProveedorServices{

    private final ProveedorRepository proveedorRepository;

    public ProveedorServicesImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDto> listarTodo() {
        return this.proveedorRepository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Optional<ProveedorDto> buscarPorId(Long id) {
        return this.proveedorRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public ProveedorDto registrar(ProveedorDto pr) {
        Proveedor prov = new Proveedor();
        prov.setNombre(pr.nombreProv());
        prov.setRuc(pr.rucProv());
        prov.setDireccion(pr.direccionProv());
        prov.setTelefono(pr.telefonoProv());
        prov.setEstado(pr.estadoProv());
        return this.convertToDto((Proveedor)this.proveedorRepository.save(prov));
    }

    @Override
    public Optional<ProveedorDto> actualizar(Long id, ProveedorDto pr) {
        return this.proveedorRepository.findById(id).map(prov -> {
            prov.setNombre(pr.nombreProv());
            prov.setRuc(pr.rucProv());
            prov.setDireccion(pr.direccionProv());
            prov.setTelefono(pr.telefonoProv());
            prov.setEstado(pr.estadoProv());
            return this.convertToDto((Proveedor)this.proveedorRepository.save(prov));
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (this.proveedorRepository.existsById(id)) {
            this.proveedorRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    private ProveedorDto convertToDto (Proveedor pr){
        return new ProveedorDto(
                pr.getIdProveedor(),
                pr.getNombre(),
                pr.getRuc(),
                pr.getTelefono(),
                pr.getDireccion(),
                pr.getEstado());
    }
}
