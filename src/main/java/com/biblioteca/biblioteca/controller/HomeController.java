package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Autor;
import com.biblioteca.biblioteca.model.Livro;
import com.biblioteca.biblioteca.repository.AutorRepository;
import com.biblioteca.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public HomeController(
            LivroRepository livroRepository,
            AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @GetMapping("/livros")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Sistema de Biblioteca");
        return "livros/inicio";
    }

    @GetMapping("/livros/cadastroLivro")
    public String livro(Model model) {
        return "livros/cadastroLivro";
    }

    @PostMapping("/livros/livroCadastrado")
    public String cadastrarLivro(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam String isbn,
            Model model) {

        Livro livro = new Livro();

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setIsbn(isbn);

        model.addAttribute("titulo", titulo);
        model.addAttribute("autor", autor);
        model.addAttribute("isbn", isbn);

        livroRepository.save(livro);

        return "livros/livroCadastrado";
    }

    @GetMapping("/livros/cadastroAutor")
    public String cadastroAutor() {
        return "livros/cadastroAutor";
    }

    @PostMapping("/livros/autorCadastrado")
    public String cadastrarAutor(
            @RequestParam String autor,
            @RequestParam String nacionalidade,
            @RequestParam int quantidadeLivros,
            Model model) {

        Autor autorObj = new Autor();

        autorObj.setAutor(autor);
        autorObj.setNacionalidade(nacionalidade);
        autorObj.setQuantidade(quantidadeLivros);

        model.addAttribute("autor", autor);
        model.addAttribute("nacionalidade", nacionalidade);
        model.addAttribute("quantidadeLivros", quantidadeLivros);

        autorRepository.save(autorObj);

        return "livros/autorCadastrado";
    }
}
