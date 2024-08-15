package ch.onsege.counter.controller;

import ch.onsege.counter.dto.CounterDto;
import ch.onsege.counter.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counter")
public class CounterController {

    private final CounterService counterService;

    @GetMapping
    public String getAllCounter(Model model) {
        List<CounterDto> counterList = counterService.getAllCounter();
        model.addAttribute("counterList", counterList);
        return "counter/index";
    }

    @GetMapping("/{counterId}")
    public String getCounterById(@PathVariable("counterId") Long id, Model model) {
        CounterDto counterDto = counterService.findById(id);
        model.addAttribute("counter", counterDto);
        return "counter/" + counterDto.getName();
    }
}
