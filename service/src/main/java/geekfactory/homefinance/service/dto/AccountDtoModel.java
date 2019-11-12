package geekfactory.homefinance.service.dto;

import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.dao.model.CurrencyModel;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoModel {
    private int id;
    private String name;
    private String amount;
    private String currencyModel;
    private String accountType;

}
