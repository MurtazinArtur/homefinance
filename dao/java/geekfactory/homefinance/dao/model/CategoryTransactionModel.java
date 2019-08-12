package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CategoryTransactionModel {
    private Long id;
    private String name;
    private CategoryTransactionModel parentCategory;

    public CategoryTransactionModel(Optional<CategoryTransactionModel> byId) {
    }

    public CategoryTransactionModel() {
    }
}
