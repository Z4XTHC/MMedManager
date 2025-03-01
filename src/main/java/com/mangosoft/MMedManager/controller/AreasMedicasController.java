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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

}
