package ch.onsege.counter.controller;

import ch.onsege.counter.dto.CounterDto;
import ch.onsege.counter.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/counter")
public class RestCounterController {

    private final CounterService counterService;

    @PostMapping("/{counterId}/increment")
    public CounterDto increase(@PathVariable("counterId") Long counterId) {
        return counterService.increase(counterId);
    }

    @PostMapping("/{counterId}/decrement")
    public CounterDto decrease(@PathVariable("counterId") Long counterId) {
        return counterService.decrease(counterId);
    }
}
