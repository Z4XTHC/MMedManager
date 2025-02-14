package com.mangosoft.MMedManager.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mangosoft.MMedManager.model.dto.UsuarioDTO;
import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.entities.Usuario;
import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iRolService;
import com.mangosoft.MMedManager.model.services.iUsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private iUsuarioService usuarioService;

    @Autowired
    private iMedicoService medicoService;

    @Autowired
    private iRolService rolService;

    @GetMapping("/lista")
    public String listUsuarios(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista de Usuarios");
        model.addAttribute("usuarios", usuarioService.buscarTodos());

        return "usuarios/list";
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setActivo(true);

        Set<Rol> roles = new HashSet<>();
        if (usuarioDTO.getRolesIds() != null && !usuarioDTO.getRolesIds().isEmpty()) {
            for (Long rolId : usuarioDTO.getRolesIds()) {
                Optional<Rol> rolOpt = rolService.buscarPorId(rolId);
                if (rolOpt.isPresent()) {
                    roles.add(rolOpt.get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol con ID " + rolId + " no encontrado.");
                }
            }
        } else {
            return ResponseEntity.badRequest().body("Debe asignar al menos un rol al usuario.");
        }
        usuario.setRoles(roles);

        usuarioService.guardar(usuario);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario guardado correctamente"));
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("id") Long id, Model model) {

        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado."));

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {

        Usuario usuarioExistente = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado."));

        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());

        Set<Rol> roles = new HashSet<>();
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            for (Rol rol : usuario.getRoles()) {
                Rol rolExistente = rolService.buscarPorId(rol.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Rol con ID " + rol.getId() + " no encontrado."));
                roles.add(rolExistente);
            }
        } else {
            return ResponseEntity.badRequest().body("Debe seleccionar al menos un rol para el usuario.");
        }
        usuarioExistente.setRoles(roles);

        usuarioService.guardar(usuarioExistente);

        return ResponseEntity.ok(Map.of("mensaje", "Usuario actualizado correctamente"));
    }

    @GetMapping("/admin-check")
    public String checkAdminUser(Model model) {
        var admin = usuarioService.buscarPorUsername("admin");

        if (admin == null) {
            model.addAttribute("mensaje", "El usuario administrador no existe en la base de datos.");
        } else {
            model.addAttribute("mensaje", "El usuario administrador está presente.");
        }

        return "usuarios/admin-check";
    }

    @ModelAttribute("roles")
    public List<Rol> getRoles() {
        return rolService.buscarTodos();
    }

}
