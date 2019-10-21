package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bank_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
}
