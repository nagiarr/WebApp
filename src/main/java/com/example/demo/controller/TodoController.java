package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class TodoController {

    private final TodoRepository repository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // ★ ログインユーザーのToDoだけ表示
    @GetMapping("/")
    public String list(Model model, Authentication auth) {

        String username = auth.getName();

        model.addAttribute("todos",
                repository.findByUserUsername(username));

        return "index";
    }

    // ★ ユーザー紐付けして保存
    @PostMapping("/add")
    public String add(@RequestParam String title, Authentication auth) {

        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        Todo todo = new Todo(title);
        todo.setUser(user);

        repository.save(todo);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}