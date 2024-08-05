package ch.onsege.others.repository;

import ch.onsege.others.entity.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRepository extends JpaRepository<Count, Long> {
}
