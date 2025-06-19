package com.sistemaescolar.CONTROLLERS;

import com.sistemaescolar.MODELS.Turma;
import com.sistemaescolar.SERVICES.TurmaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public String listarTurmas(Model model) {
        model.addAttribute("turmas", turmaService.listarTodas());
        return "turmas/listar-turmas";
    }

    @GetMapping("/novo")
    public String novaTurmaForm(Model model) {
        model.addAttribute("turma", new Turma());
        return "turmas/form-turma";
    }

    @PostMapping("/salvar")
    public String salvarTurma(@ModelAttribute Turma turma) {
        turmaService.salvar(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editarTurma(@PathVariable Long id, Model model) {
        Turma turma = turmaService.buscarPorId(id).orElseThrow();
        model.addAttribute("turma", turma);
        return "turmas/form-turma";
    }

    @GetMapping("/deletar/{id}")
    public String deletarTurma(@PathVariable Long id) {
        turmaService.deletar(id);
        return "redirect:/turmas";
    }
}
