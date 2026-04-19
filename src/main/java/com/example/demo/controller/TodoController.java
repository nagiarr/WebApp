package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Todo;

@Controller
public class TodoController {

    private List<Todo> todos = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title) {
        todos.add(new Todo(title));
        System.out.println("追加" + title);
        return "redirect:/";
    }

}
