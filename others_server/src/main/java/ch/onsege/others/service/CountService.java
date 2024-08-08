package ch.onsege.others.service;

import ch.onsege.others.dto.CountDto;
import ch.onsege.others.entity.Count;
import ch.onsege.others.repository.CountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountService {

    private final CountRepository countRepository;

    public List<CountDto> getAllCount() {
        return countRepository.findAll()
                .stream()
                .map(CountDto::new)
                .toList();
    }

    public CountDto findById(Long id) {
        Count count = countRepository.findById(id).orElseThrow();
        return new CountDto(count);
    }

    @Transactional
    public void create(String countName) {
        if (existName(countName)) throw new IllegalArgumentException("Count name already exists");

        Count count = new Count(countName);
        countRepository.save(count);
    }

    private boolean existName(String countName) {
        for (Count count : countRepository.findAll()) {
            if (count.getName().equals(countName)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public void increase(Long id) {
        Optional<Count> countOpt = countRepository.findById(id);
        if (countOpt.isEmpty()) return;

        Count count = countOpt.get();
        count.increase();
    }

    @Transactional
    public void decrease(Long id) {
        Optional<Count> countOpt = countRepository.findById(id);
        if (countOpt.isEmpty()) return;

        Count count = countOpt.get();
        count.decrease();
    }
}
