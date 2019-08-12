package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionModel {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
    private String source;
    private Collection<CategoryTransactionModel> category;
    private BankModel bank;
    private AccountModel account;
    private CurrencyModel currency;

    public TransactionModel() {
    }
}
