package geekfactory.homefinance.dao.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyModel {
    private Long id;
    private String name;
    private String code;
    private String symbol;
}
