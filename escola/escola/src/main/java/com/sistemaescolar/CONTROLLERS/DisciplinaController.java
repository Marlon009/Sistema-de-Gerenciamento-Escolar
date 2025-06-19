package com.sistemaescolar.CONTROLLERS;

import com.sistemaescolar.MODELS.Disciplina;
import com.sistemaescolar.SERVICES.DisciplinaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String listarDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaService.listarTodas());
        return "disciplinas/listar-disciplinas";
    }

    @GetMapping("/novo")
    public String novaDisciplinaForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplinas/form-disciplina";
    }

    @PostMapping("/salvar")
    public String salvarDisciplina(@ModelAttribute Disciplina disciplina) {
        disciplinaService.salvar(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/editar/{id}")
    public String editarDisciplina(@PathVariable Long id, Model model) {
        Disciplina disciplina = disciplinaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina inválida: " + id));

        model.addAttribute("disciplina", disciplina);
        return "disciplinas/form-disciplina";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletar(id);
        return "redirect:/disciplinas";
    }
}
