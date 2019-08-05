package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionTypeModel {
    private long id;
    private String name;

    public TransactionTypeModel() {
    }
}
