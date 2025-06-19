package com.sistemaescolar.CONTROLLERS;

import com.sistemaescolar.MODELS.Nota;
import com.sistemaescolar.SERVICES.AlunoService;
import com.sistemaescolar.SERVICES.DisciplinaService;
import com.sistemaescolar.SERVICES.NotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;

    public NotaController(NotaService notaService, AlunoService alunoService, DisciplinaService disciplinaService) {
        this.notaService = notaService;
        this.alunoService = alunoService;
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String listarNotas(Model model) {
        model.addAttribute("notas", notaService.listarTodas());
        return "notas/listar";
    }

    @GetMapping("/nova")
    public String novaNota(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        model.addAttribute("disciplinas", disciplinaService.listarTodas());
        return "notas/form";
    }

    @PostMapping("/salvar")
    public String salvarNota(@RequestParam Long alunoId,
                             @RequestParam Long disciplinaId,
                             @RequestParam Double notaP1,
                             @RequestParam Double notaP2,
                             @RequestParam Double notaT5) {

        Nota nota = new Nota();
        nota.setAluno(alunoService.buscarPorId(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado")));
        nota.setDisciplina(disciplinaService.buscarPorId(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada")));
        nota.setNotaP1(notaP1);
        nota.setNotaP2(notaP2);
        nota.setNotaT5(notaT5);

        notaService.salvar(nota);
        return "redirect:/notas";
    }

}
