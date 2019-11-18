package geekfactory.homefinance.dao.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_tbl")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Collection<AccountModel> accountModel;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRoles userRole;
}
