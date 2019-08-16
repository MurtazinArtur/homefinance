package geekfactory.homefinance.dao.model;

import lombok.*;

import java.util.Optional;

@Data
@NoArgsConstructor
public class CategoryTransactionModel {
    private Long id;
    private String name;
    private CategoryTransactionModel parentCategory;

    public CategoryTransactionModel(Optional<CategoryTransactionModel> byId) {
    }
}
