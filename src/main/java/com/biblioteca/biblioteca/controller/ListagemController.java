package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.repository.AutorRepository;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.biblioteca.biblioteca.model.Livro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biblioteca.biblioteca.model.Autor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListagemController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public ListagemController(
            LivroRepository livroRepository,
            AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping("/livros/listaCadastroLivro")
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());

        return "livros/listaCadastroLivro";
    }

    @GetMapping("/livros/listaCadastroAutor")
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());

        return "livros/ListaCadastroAutor";
    }

    @PostMapping("/livros/editarLivro")
    public String editarLivro(
            @RequestParam Long id,
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam String isbn) {

        Livro livro = livroRepository.findById(id).orElse(null);

        if (livro != null) {
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setIsbn(isbn);

            livroRepository.save(livro);
        }

        return "redirect:/livros/listaCadastroLivro";
    }

    @PostMapping("/livros/excluirLivro")
    public String excluirLivro(@RequestParam Long id) {

        livroRepository.deleteById(id);

        return "redirect:/livros/listaCadastroLivro";
    }

    @PostMapping("/livros/editarAutor")
    public String editarAutor(
            @RequestParam Long id,
            @RequestParam String autor,
            @RequestParam String nacionalidade,
            @RequestParam int quantidade) {

        Autor autorEncontrado = autorRepository.findById(id).orElse(null);

        if (autorEncontrado != null) {
            autorEncontrado.setAutor(autor);
            autorEncontrado.setNacionalidade(nacionalidade);
            autorEncontrado.setQuantidade(quantidade);

            autorRepository.save(autorEncontrado);
        }

        return "redirect:/livros/listaCadastroAutor";
    }

    @PostMapping("/livros/excluirAutor")
    public String excluirAutor(@RequestParam Long id) {

        autorRepository.deleteById(id);

        return "redirect:/livros/listaCadastroAutor";
    }
}
