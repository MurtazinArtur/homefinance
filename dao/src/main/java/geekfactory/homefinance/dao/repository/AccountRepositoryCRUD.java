package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.AccountModel;
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
@Repository("accountRepository")
public class AccountRepositoryCRUD {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<AccountModel> findByName(String name) {
        TypedQuery<AccountModel> query =
                entityManager.createQuery("SELECT account FROM AccountModel account " +
                        "WHERE account.name = :name", AccountModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<AccountModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(AccountModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<AccountModel> findAll() {
        return (Collection<AccountModel>) entityManager.createQuery("SELECT account FROM AccountModel account").getResultList();
    }

    @Transactional
    public void remove(AccountModel model) {
        AccountModel modelToDelete = entityManager.find(AccountModel.class, model.getId());
        entityManager.remove(modelToDelete);
    }

    @Transactional
    public void save(AccountModel model) {
        entityManager.persist(model);
    }

    @Transactional
    public AccountModel update(AccountModel model) {
        entityManager.merge(model);
        return model;
    }
}