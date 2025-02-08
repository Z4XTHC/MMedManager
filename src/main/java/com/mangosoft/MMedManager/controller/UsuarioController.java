package com.mangosoft.MMedManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private iUsuarioService usuarioService;

    @Autowired
    private iMedicoService medicoService;

    @GetMapping("/lista")
    public String listUsuarios(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista de Usuarios");
        model.addAttribute("usuarios", usuarioService.buscarTodos());

        return "usuarios/list";
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

}
