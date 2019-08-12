package geekfactory.homefinance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BankModel {
    private Long id;
    private String name;

    public BankModel() {
    }
}
