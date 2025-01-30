package com.mangosoft.MMedManager.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.mangosoft.MMedManager.model.entities.Paciente;
import com.mangosoft.MMedManager.model.services.iPacienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private iPacienteService pacienteService;

    @GetMapping("/lista")
    public String listaPacientes(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista Pacientes");
        model.addAttribute("pacientes", pacienteService.buscarTodos());

        return "pacientes/list";
    }

    @PostMapping("/generar")
    public ResponseEntity<?> generarPaciente(@Valid @RequestBody Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        if (paciente.getId() != null && paciente.getId() > 0) {
            Optional<Paciente> pacienteExistente = pacienteService.buscarPorId(paciente.getId());
            if (pacienteExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
            }
        }

        if (paciente.getObraSocial().isEmpty()) {
            paciente.setObraSocial("Sin Datos");
        }

        pacienteService.guardar(paciente);
        return ResponseEntity.ok().build();
    }

}
