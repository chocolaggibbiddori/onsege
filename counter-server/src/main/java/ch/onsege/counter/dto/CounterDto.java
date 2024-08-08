package ch.onsege.counter.dto;

import ch.onsege.counter.entity.Counter;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CounterDto {

    private Long id;
    private String name;
    private int value;

    public CounterDto(Counter counter) {
        this.id = counter.getId();
        this.name = counter.getName();
        this.value = counter.getValue();
    }
}
