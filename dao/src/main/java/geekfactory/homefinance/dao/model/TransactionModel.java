package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "transaction_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {
    @Id
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "source")
    private String source;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "transaction_category_tbl",
            joinColumns = @JoinColumn(name = "transaction_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<CategoryTransactionModel> category;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankModel bank;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountModel account;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyModel currency;
}