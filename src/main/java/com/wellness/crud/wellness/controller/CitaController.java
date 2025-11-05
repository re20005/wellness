package com.wellness.crud.wellness.controller;

import com.wellness.crud.wellness.model.Cita;
import com.wellness.crud.wellness.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/citas")
    public String listarCitas(Model model) {
        List<Cita> citas = citaService.obtenerTodas();
        model.addAttribute("citas", citas);
        return "cita/lista"; // 
    }

    @GetMapping("/nueva")
    public String nueva() {
        return "cita/nueva";
    }

    @GetMapping("/reprogramar")
    public String reprogramar() {
        return "cita/reprogramar";
    }

    @GetMapping("/eliminar")
    public String eliminar() {
        return "cita/eliminar";
    }

    @GetMapping("/historial")
    public String historial() {
        return "cita/historial";
    }
}
