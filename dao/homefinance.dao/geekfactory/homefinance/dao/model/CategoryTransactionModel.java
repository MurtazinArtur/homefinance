package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CategoryTransactionModel {
    private long id;
    private String name;
    private CategoryTransactionModel parentCategory;

    public CategoryTransactionModel() {
    }
}
