package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.UserModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@EnableTransactionManagement
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Repository("userRepository")
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<UserModel> findByName(String user) {
        TypedQuery<UserModel> query =
                entityManager.createQuery("SELECT userModel FROM UserModel userModel " +
                        "WHERE userModel.user = :user", UserModel.class);
        query.setParameter("user", user);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<UserModel> findAll() {
        return (Collection<UserModel>) entityManager.createQuery(
                "SELECT userModel FROM UserModel userModel").getResultList();
    }

    @Transactional
    public void save(UserModel userModel) {
        entityManager.persist(userModel);
    }

    @Transactional
    public void remove(UserModel userModel) {
        UserModel userModelToDelete = entityManager.find(UserModel.class, userModel.getId());
        entityManager.remove(userModelToDelete);
    }

    @Transactional
    public UserModel update(UserModel userModel) {
        entityManager.merge(userModel);

        return userModel;
    }
}