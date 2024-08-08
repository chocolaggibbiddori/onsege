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
    public void create(String counterName) {
        if (existName(counterName)) throw new IllegalArgumentException("Counter name already exists");

        Counter counter = new Counter(counterName);
        counterRepository.save(counter);
    }

    private boolean existName(String counterName) {
        for (Counter counter : counterRepository.findAll()) {
            if (counter.getName().equals(counterName)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public void increase(String counterName) {
        Optional<Counter> counterOpt = counterRepository.findByName(counterName);
        if (counterOpt.isEmpty()) return;

        Counter counter = counterOpt.get();
        counter.increase();
    }

    @Transactional
    public void decrease(String counterName) {
        Optional<Counter> counterOpt = counterRepository.findByName(counterName);
        if (counterOpt.isEmpty()) return;

        Counter counter = counterOpt.get();
        counter.decrease();
    }
}
