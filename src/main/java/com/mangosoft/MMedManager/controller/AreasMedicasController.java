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
import org.springframework.web.bind.annotation.RequestMapping;

import com.mangosoft.MMedManager.model.entities.AreaMedica;
import com.mangosoft.MMedManager.model.services.iAreaMedicaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/areas-medicas")
public class AreasMedicasController {

    @Autowired
    private iAreaMedicaService areaMedicaService;

    // Listar Áreas Médicas
    @GetMapping("/lista")
    public String listaAreasMedicas(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista Áreas Médicas");
        model.addAttribute("areas_medicas", areaMedicaService.buscarTodos());

        return "areasMedicas/list";
    }

    // Generar un área médica desde Modal
    @PostMapping("/generar")
    public ResponseEntity<?> generarAreasMedicas(@Valid @RequestBody AreaMedica areaMedica, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        if (areaMedica.getId() != null && areaMedica.getId() > 0) {
            Optional<AreaMedica> areaMedicaExistente = areaMedicaService.buscarPorId(areaMedica.getId());
            if (areaMedicaExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Área médica no encontrada.");
            }
        }

        areaMedicaService.guardar(areaMedica);

        return ResponseEntity.ok().build();
    }

    // Editar un área médica
    @GetMapping("/editar/{id}")
    public ResponseEntity<?> editarAreasMedicas(@PathVariable("id") Long id, Model model) {

        Optional<AreaMedica> areaMedica = areaMedicaService.buscarPorId(id);
        if (areaMedica.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Área Médica no encontrada.");
        }

        return ResponseEntity.ok(areaMedica);
    }

    // Actualizar Área Médica
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarAreasMedicas(@PathVariable("id") Long id, @RequestBody AreaMedica areaMedica) {

        AreaMedica areaMedicaExistente = areaMedicaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área Médica no encontrada."));

        areaMedicaExistente.setNombre(areaMedica.getNombre());
        areaMedicaExistente.setDescripcion(areaMedica.getDescripcion());

        areaMedicaService.guardar(areaMedicaExistente);

        return ResponseEntity.ok(Map.of("mensaje", "El Área Médica se ha actualizado correctamente.\""));
    }
}
