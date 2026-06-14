package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Это для страниц, а не для текста
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index"; // Верни файл index.html из папки templates
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // Верни файл about.html
    }
}
