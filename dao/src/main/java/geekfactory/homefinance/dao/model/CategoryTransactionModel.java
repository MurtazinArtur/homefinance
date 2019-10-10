package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTransactionModel {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private CategoryTransactionModel parentCategory;
}