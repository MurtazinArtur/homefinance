package geekfactory.homefinance.service.dto;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDtoModel {
    private int id;
    private String name;
    private String code;
    private String symbol;
}
