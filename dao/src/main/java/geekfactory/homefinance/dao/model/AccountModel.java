package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyModel currencyModel;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;
}
