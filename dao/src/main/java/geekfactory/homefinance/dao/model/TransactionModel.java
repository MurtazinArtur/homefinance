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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "source")
    private String source;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_category_tbl",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<CategoryTransactionModel> category;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bank_id")
    private BankModel bank;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    private AccountModel account;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id")
    private CurrencyModel currency;
}