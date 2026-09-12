package com.sanmartin.farmacias.Controller;

import com.sanmartin.farmacias.Services.IPersonaServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/v1/persona"})
public class PersonaController {
    private final IPersonaServices PersonaServices;

    public PersonaController(IPersonaServices personaServices) {
        PersonaServices = personaServices;
    }
}
