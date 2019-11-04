package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "currency_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "symbol")
    private String symbol;
}
