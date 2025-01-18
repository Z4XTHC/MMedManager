package com.mangosoft.MMedManager.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String iniciarSesion(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model, Principal principal, RedirectAttributes attribute) {

        model.addAttribute("titulo", "Med Manager - Login");

        if (error != null) {
            model.addAttribute("sesionError", "ERROR");
        }

        if (principal != null) {
            attribute.addFlashAttribute("sesionIniciada", "INICIADO");
            return "redirect:/";
        }

        if (logout != null) {
            attribute.addFlashAttribute("sesionFinalizada", "FINALIZADO");
            return "redirect:/";
        }

        return "login";
    }
}
