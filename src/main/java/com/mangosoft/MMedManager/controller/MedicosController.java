package com.mangosoft.MMedManager.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mangosoft.MMedManager.model.entities.AreaMedica;
import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.entities.Usuario;
import com.mangosoft.MMedManager.model.services.iAreaMedicaService;
import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iUsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private iMedicoService medicoService;

    @Autowired
    private iAreaMedicaService areaMedicaService;

    @Autowired
    private iUsuarioService usuarioService;

    @GetMapping("/lista")
    public String listaMedicos(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista Médicos");
        model.addAttribute("medicos", medicoService.buscarTodos());
        return "medicos/list";
    }

    @PostMapping("/generar")
    public ResponseEntity<?> generarMedico(@Valid @RequestBody Medico medico, BindingResult result) {
        // Validación de errores
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        // Aquí verificamos si la relación con AreaMedica y Usuario está bien
        // configurada
        if (medico.getAreaMedica() != null) {
            Long areaMedicaId = medico.getAreaMedica().getId();
            Optional<AreaMedica> areaMedica = areaMedicaService.buscarPorId(areaMedicaId);
            if (areaMedica.isPresent()) {
                medico.setAreaMedica(areaMedica.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Área médica no válida.");
            }
        }

        if (medico.getUsuario() != null) {
            Long usuarioId = medico.getUsuario().getId();
            Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);
            if (usuario.isPresent()) {
                medico.setUsuario(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no válido.");
            }
        }

        // Guardar el médico en la base de datos
        medicoService.guardar(medico);
        return ResponseEntity.ok().build();
    }

    @ModelAttribute("areaMedicas")
    public List<AreaMedica> getAreaMedicas() {
        return areaMedicaService.buscarTodos();
    }

    @ModelAttribute("usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioService.buscarTodos();
    }

}
