package geekfactory.homefinance.service.dto;

import lombok.*;

import java.util.Collection;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoModel {
    private int id;
    private String date;
    private String source;
    private Collection<String> category;
    private String amount;
    private String currency;
    private String account;
    private String bank;
}
