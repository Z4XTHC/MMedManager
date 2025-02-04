package com.mangosoft.MMedManager.controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.services.iRolService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private iRolService rolService;

    @GetMapping("/lista")
    public String getMethodName(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista de Roles");
        model.addAttribute("roles", rolService.buscarTodos());

        return "roles/list";
    }

    @PostMapping("/generar")
    public ResponseEntity<?> generarRol(@Valid @RequestBody Rol rol, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        if (rol.getId() != null && rol.getId() > 0) {
            Optional<Rol> rolExistente = rolService.buscarPorId(rol.getId());
            if (rolExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado.");
            }
        }

        rolService.guardar(rol);

        return ResponseEntity.ok().build();
    }
}
