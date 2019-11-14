package geekfactory.homefinance.service.dto;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoModel {
    private int id;
    private String name;
    private String parentCategory;
}
