package com.sistemaescolar.CONTROLLERS;

import com.sistemaescolar.MODELS.Aluno;
import com.sistemaescolar.MODELS.Nota;
import com.sistemaescolar.SERVICES.AlunoService;
import com.sistemaescolar.SERVICES.DisciplinaService; // ✅ Adicione
import com.sistemaescolar.SERVICES.NotaService;
import com.sistemaescolar.SERVICES.TurmaService; // ✅ Adicione
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private final DisciplinaService disciplinaService;
    private final NotaService notaService;


    public AlunoController(AlunoService alunoService,
                           TurmaService turmaService, // ✅ Adicione
                           DisciplinaService disciplinaService, NotaService notaService) { // ✅ Adicione
        this.alunoService = alunoService;
        this.turmaService = turmaService; // ✅ Inicialize
        this.disciplinaService = disciplinaService; // ✅ Inicialize
        this.notaService = notaService;
    }

    @GetMapping
    public String listarAlunos(Model model) {
        List<Aluno> alunos = alunoService.listarTodos();
        for (Aluno aluno : alunos) {
            List<Nota> notasDoAluno = notaService.buscarPorAluno(aluno.getId());
            aluno.setNotas(notasDoAluno);  // Você precisa criar esse setter em Aluno, ou outro mecanismo para guardar as notas.
        }
        model.addAttribute("alunos", alunos);
        return "alunos/listar";  // seu template de listagem de alunos
    }

    @GetMapping("/novo")
    public String novoAlunoForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("turmas", turmaService.listarTodas()); // ✅
        model.addAttribute("disciplinas", disciplinaService.listarTodas()); // ✅
        return "alunos/form";
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        Aluno aluno = alunoService.buscarPorId(id).orElseThrow();
        model.addAttribute("aluno", aluno);
        model.addAttribute("turmas", turmaService.listarTodas()); // ✅
        model.addAttribute("disciplinas", disciplinaService.listarTodas()); // ✅
        return "alunos/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        alunoService.deletar(id);
        return "redirect:/alunos";
    }
}
