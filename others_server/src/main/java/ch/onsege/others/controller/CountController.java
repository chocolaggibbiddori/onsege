package ch.onsege.others.controller;

import ch.onsege.others.dto.CountDto;
import ch.onsege.others.service.CountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/count")
public class CountController {

    private final CountService countService;

    @GetMapping
    public String getAllCount(Model model) {
        List<CountDto> countList = countService.getAllCount();
        model.addAttribute("countList", countList);
        return "count/index";
    }

    @GetMapping("/{countId}")
    public String getCountById(@PathVariable("countId") Long id, Model model) {
        CountDto countDto = countService.findById(id);
        model.addAttribute("count", countDto);
        model.addAttribute("max", 70);
        model.addAttribute("goal", 50);

        return "count/" + countDto.getName();
    }

    @GetMapping("/create")
    public String createCount() {
        return "count/create";
    }

    @PostMapping
    public String createCount(@RequestParam("name") String countName) {
        countService.create(countName);
        return "redirect:/count";
    }

    @PostMapping("/{countId}/increment")
    public String increase(CountDto countDto) {
        countService.increase(countDto.getId());
        return "count/" + countDto.getName();
    }

    @PostMapping("/{countId}/decrement")
    public String decrease(CountDto countDto) {
        countService.decrease(countDto.getId());
        return "count/" + countDto.getName();
    }
}
