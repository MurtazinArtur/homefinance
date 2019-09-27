package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTransactionModel implements Comparable<CategoryTransactionModel> {
    private Long id;
    private String name;
    private CategoryTransactionModel parentCategory;

    public CategoryTransactionModel(Optional<CategoryTransactionModel> byId) {

    }

    @Override
    public int compareTo(CategoryTransactionModel model) {
        return (int) (this.id - model.id);
    }
}
