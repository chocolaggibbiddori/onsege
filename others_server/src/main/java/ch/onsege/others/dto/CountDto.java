package ch.onsege.others.dto;

import ch.onsege.others.entity.Count;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CountDto {

    private Long id;
    private String name;
    private int value;

    public CountDto(Count count) {
        this.id = count.getId();
        this.name = count.getName();
        this.value = count.getValue();
    }

    public Count newCount() {
        return new Count(name);
    }
}
