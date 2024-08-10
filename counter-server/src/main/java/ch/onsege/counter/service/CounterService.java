package ch.onsege.counter.service;

import ch.onsege.counter.dto.CounterDto;
import ch.onsege.counter.entity.Counter;
import ch.onsege.counter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CounterService {

    private final CounterRepository counterRepository;

    public List<CounterDto> getAllCounter() {
        return counterRepository.findAll()
                .stream()
                .map(CounterDto::new)
                .toList();
    }

    public CounterDto findById(Long id) {
        Counter count = counterRepository.findById(id).orElseThrow();
        return new CounterDto(count);
    }

    @Transactional
    public CounterDto increase(Long counterId) {
        Optional<Counter> counterOpt = counterRepository.findById(counterId);
        if (counterOpt.isEmpty()) throw new IllegalArgumentException("There is no counter");

        Counter counter = counterOpt.get();
        counter.increase();

        return new CounterDto(counter);
    }

    @Transactional
    public CounterDto decrease(Long counterId) {
        Optional<Counter> counterOpt = counterRepository.findById(counterId);
        if (counterOpt.isEmpty()) throw new IllegalArgumentException("There is no counter");

        Counter counter = counterOpt.get();
        counter.decrease();

        return new CounterDto(counter);
    }
}
