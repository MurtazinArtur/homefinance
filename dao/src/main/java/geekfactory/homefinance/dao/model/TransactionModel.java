package geekfactory.homefinance.dao.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
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
}
