package geekfactory.homefinance.service.dto;

import lombok.*;

import java.util.Collection;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoModel {
    private int id;
    private String user;
    private String password;
    private String userRole;
}