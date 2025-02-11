package com.mangosoft.MMedManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.services.iRolService;

@Controller
public class HomeController {

    @Autowired
    private iRolService rolService;

    @GetMapping({ "/home", "/" })
    public String home(Model model) {

        model.addAttribute("titulo", "Med Manager - Inicio");

        return "home";
    }

    @ModelAttribute("roles")
    public List<Rol> getRoles() {
        return rolService.buscarTodos();
    }
}
