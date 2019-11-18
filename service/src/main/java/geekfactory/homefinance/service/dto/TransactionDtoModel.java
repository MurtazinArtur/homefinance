package geekfactory.homefinance.service.dto;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.model.CurrencyModel;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoModel {
    private int id;
    private String date;
    private String source;
    private Collection<String> category;
    private String amount;
    private String currency;
    private String account;
    private String bank;
}
