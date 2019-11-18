package geekfactory.homefinance.service.dto;

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
