package com.mangosoft.MMedManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mangosoft.MMedManager.model.services.iPacienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
