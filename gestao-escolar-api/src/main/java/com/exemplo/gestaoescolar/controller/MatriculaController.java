package com.exemplo.gestaoescolar.controller;

import com.exemplo.gestaoescolar.model.Matricula;
import com.exemplo.gestaoescolar.repository.MatriculaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    private final MatriculaRepository repo;

    public MatriculaController(MatriculaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Matricula> listar() { return repo.findAll(); }

    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) { return repo.save(matricula); }

    @GetMapping("/{id}")
    public Matricula buscar(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { repo.deleteById(id); }
}
