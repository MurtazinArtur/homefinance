package geekfactory.homefinance.dao.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    private Long id;
    private CurrencyModel currencyModel;
    private String name;
    private AccountType accountType;
    private BigDecimal amount;
}
