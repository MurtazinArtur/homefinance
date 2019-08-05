package geekfactory.homefinance.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrencyModel {
    private Long id;
    private String name;
    private String code;
    private String symbol;

    public CurrencyModel(Long id, String name, String code, String symbol) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.symbol = symbol;
    }

    public CurrencyModel() {
    }
}
