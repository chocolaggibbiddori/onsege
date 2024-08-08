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
        model.addAttribute("max", 70);
        model.addAttribute("goal", 50);

        return "counter/" + counterDto.getName();
    }

    @GetMapping("/create")
    public String createCounter() {
        return "counter/create";
    }

    @PostMapping
    public String createCounter(@RequestParam("name") String counterName) {
        counterService.create(counterName);
        return "redirect:/counter";
    }

    @PostMapping("/{counterName}/increment")
    public String increase(@PathVariable("counterName") String counterName) {
        counterService.increase(counterName);
        return "counter/" + counterName;
    }

    @PostMapping("/{counterName}/decrement")
    public String decrease(@PathVariable("counterName") String counterName) {
        counterService.decrease(counterName);
        return "counter/" + counterName;
    }
}
