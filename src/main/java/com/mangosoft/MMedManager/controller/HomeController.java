package com.mangosoft.MMedManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mangosoft.MMedManager.model.entities.AreaMedica;
import com.mangosoft.MMedManager.model.entities.Medico;
import com.mangosoft.MMedManager.model.entities.Paciente;
import com.mangosoft.MMedManager.model.entities.Rol;
import com.mangosoft.MMedManager.model.entities.Usuario;
import com.mangosoft.MMedManager.model.services.iAreaMedicaService;
import com.mangosoft.MMedManager.model.services.iCitaService;
import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iPacienteService;
import com.mangosoft.MMedManager.model.services.iRolService;
import com.mangosoft.MMedManager.model.services.iUsuarioService;

@Controller
public class HomeController {

    @Autowired
    private iRolService rolService;
    @Autowired
    private iCitaService citaService;
    @Autowired
    private iPacienteService pacienteService;
    @Autowired
    private iMedicoService medicoService;
    @Autowired
    private iAreaMedicaService areaMedicaService;
    @Autowired
    private iUsuarioService usuarioService;

    @GetMapping({ "/home", "/" })
    public String home(Model model) {

        model.addAttribute("titulo", "Med Manager - Inicio");

        return "home";
    }

    @ModelAttribute("roles")
    public List<Rol> getRoles() {
        return rolService.buscarTodos();
    }

    @ModelAttribute("pacientes")
    public List<Paciente> getPacientes() {
        return pacienteService.buscarTodos();
    }

    @ModelAttribute("medicos")
    public List<Medico> getMedico() {
        return medicoService.buscarTodos();
    }

    @ModelAttribute("areaMedicas")
    public List<AreaMedica> getAreaMedicas() {
        return areaMedicaService.buscarTodos();
    }

    @ModelAttribute("usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioService.buscarTodos();
    }

}
