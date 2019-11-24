package geekfactory.homefinance.service.dto;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoModel {
    private int id;
    private String name;
    private String amount;
    private String currencyModel;
    private String accountType;
    private String userModel;
}
