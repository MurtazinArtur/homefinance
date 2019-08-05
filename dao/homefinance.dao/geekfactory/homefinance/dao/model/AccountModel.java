package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class AccountModel {
    private long id;
    private CurrencyModel currencyModel;
    private String name;
    private AccountType accountType;
    private BigDecimal amount;

    public AccountModel() {
    }
}
