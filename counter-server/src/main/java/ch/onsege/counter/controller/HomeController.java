package ch.onsege.counter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/counter";
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "{\"status\": \"UP\"}";
    }
}
