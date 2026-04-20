package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@Controller
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("todos", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title) {
        repository.save(new Todo(title));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
