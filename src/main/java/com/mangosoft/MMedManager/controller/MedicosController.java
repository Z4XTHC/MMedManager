package com.mangosoft.MMedManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mangosoft.MMedManager.model.entities.AreaMedica;
import com.mangosoft.MMedManager.model.entities.Usuario;
import com.mangosoft.MMedManager.model.services.iAreaMedicaService;
import com.mangosoft.MMedManager.model.services.iMedicoService;
import com.mangosoft.MMedManager.model.services.iUsuarioService;

@Controller
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private iMedicoService medicoService;

    @Autowired
    private iAreaMedicaService areaMedicaService;

    @Autowired
    private iUsuarioService usuarioService;

    @GetMapping("/lista")
    public String listaMedicos(Model model) {

        model.addAttribute("titulo", "Med Manager - Lista Médicos");
        model.addAttribute("medicos", medicoService.buscarTodos());
        return "medicos/list";
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
