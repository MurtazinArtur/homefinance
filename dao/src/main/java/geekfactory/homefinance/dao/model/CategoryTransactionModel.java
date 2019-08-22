package geekfactory.homefinance.dao.model;

import lombok.*;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTransactionModel {
    private Long id;
    private String name;
    private CategoryTransactionModel parentCategory;

}
