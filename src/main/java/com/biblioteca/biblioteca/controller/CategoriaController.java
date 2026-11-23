package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/livros/cadastroCategoria")
    public String cadastroCategoria() {
        return "livros/cadastroCategoria";
    }

    @PostMapping("/livros/categoriaCadastrada")
    public String categoriaCadastrada(@RequestParam String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        repository.save(categoria);

        return "redirect:/livros/listaCadastroCategoria";
    }

    @GetMapping("/livros/listaCadastroCategoria")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", repository.findAll());

        return "livros/listaCadastroCategoria";
    }

    @PostMapping("/livros/editarCategoria")
    public String editarCategoria(
            @RequestParam int id,
            @RequestParam String nome) {

        Categoria categoria = repository.findById(id).orElse(null);

        if (categoria != null) {
            categoria.setNome(nome);
            repository.save(categoria);
        }

        return "redirect:/livros/listaCadastroCategoria";
    }

    @PostMapping("/livros/excluirCategoria")
    public String excluirCategoria(@RequestParam int id) {
        repository.deleteById(id);

        return "redirect:/livros/listaCadastroCategoria";
    }
}
