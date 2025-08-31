package com.exemplo.gestaoescolar.controller;

import com.exemplo.gestaoescolar.model.Curso;
import com.exemplo.gestaoescolar.repository.CursoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoRepository repo;

    public CursoController(CursoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Curso> listar() { return repo.findAll(); }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) { return repo.save(curso); }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Curso existente = repo.findById(id).orElseThrow();
        existente.setNome(curso.getNome());
        existente.setDescricao(curso.getDescricao());
        existente.setCargaHoraria(curso.getCargaHoraria());
        return repo.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { repo.deleteById(id); }
}
