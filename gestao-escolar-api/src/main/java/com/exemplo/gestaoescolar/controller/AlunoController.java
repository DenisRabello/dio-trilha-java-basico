package com.exemplo.gestaoescolar.controller;

import com.exemplo.gestaoescolar.model.Aluno;
import com.exemplo.gestaoescolar.repository.AlunoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoRepository repo;

    public AlunoController(AlunoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Aluno> listar() { return repo.findAll(); }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) { return repo.save(aluno); }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno existente = repo.findById(id).orElseThrow();
        existente.setNome(aluno.getNome());
        existente.setEmail(aluno.getEmail());
        existente.setDataNascimento(aluno.getDataNascimento());
        return repo.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { repo.deleteById(id); }
}
