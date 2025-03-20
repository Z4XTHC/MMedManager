package com.mangosoft.MMedManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.entities.Paciente;
import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.services.iCitaService;
import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iPacienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/citas")
public class CitasController {

    @Autowired
    private iCitaService citaService;
    @Autowired
    private iPacienteService pacienteService;
    @Autowired
    private iMedicoService medicoService;

    @GetMapping("/lista")
    public String listaCitas(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista de Citas");
        model.addAttribute("citas", citaService.buscarTodos());

        return "citas/list";
    }

    @ModelAttribute("pacientes")
    public List<Paciente> getPacientes() {
        return pacienteService.buscarTodos();
    }

    @ModelAttribute("medicos")
    public List<Medico> getMedico() {
        return medicoService.buscarTodos();
    }

}
