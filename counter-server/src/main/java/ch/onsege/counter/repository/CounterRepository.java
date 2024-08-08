package ch.onsege.counter.repository;

import ch.onsege.counter.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter, Long> {

    Optional<Counter> findByName(String name);
}
