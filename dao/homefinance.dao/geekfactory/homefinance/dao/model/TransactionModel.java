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
    long id;
    BigDecimal amount;
    LocalDateTime date;
    String source;
    Collection<CategoryTransactionModel> category;
    Collection<TransactionTypeModel> type;
    AccountModel account;

    public TransactionModel() {
    }
}
