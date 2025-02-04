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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/editar/{id}")
    public ResponseEntity<?> obtenerPaciente(@PathVariable("id") Long id, Model model) {

        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if (paciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
        }

        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteService.buscarPorId(id);
        if (pacienteExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
        }

        pacienteExistente.get().setNombre(paciente.getNombre());
        pacienteExistente.get().setApellido(paciente.getApellido());
        pacienteExistente.get().setDni(paciente.getDni());
        pacienteExistente.get().setDireccion(paciente.getDireccion());
        pacienteExistente.get().setTelefono(paciente.getTelefono());
        pacienteExistente.get().setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteExistente.get().setObraSocial(paciente.getObraSocial());

        pacienteService.guardar(pacienteExistente.get());

        return ResponseEntity.ok(Map.of("mensaje", "Paciente actualizado correctamente"));
    }

}
