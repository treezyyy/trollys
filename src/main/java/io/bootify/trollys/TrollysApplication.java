package io.bootify.trollys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@Controller
public class TrollysApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TrollysApplication.class, args);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf with Bootstrap!");
        return "index";
    }

}
