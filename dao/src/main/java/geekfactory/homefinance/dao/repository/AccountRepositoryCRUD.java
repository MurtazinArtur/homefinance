package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.AccountModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;


@Repository("accountRepository")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountRepositoryCRUD implements RepositoryCRUD<AccountModel, Long> {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountModel> findByName(String name) {
        TypedQuery<AccountModel> query =
                entityManager.createQuery("SELECT account FROM AccountModel account " +
                        "WHERE account.name = :name", AccountModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(AccountModel.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<AccountModel> findAll() {
        return (Collection<AccountModel>) entityManager.createQuery("SELECT account FROM AccountModel account").getResultList();
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        AccountModel modelToDelete = entityManager.find(AccountModel.class, id);
        entityManager.remove(modelToDelete);
        return true;
    }

    @Transactional
    @Override
    public void save(AccountModel model) {
        entityManager.persist(model);
    }

    @Transactional
    @Override
    public void update(AccountModel model) {
        entityManager.merge(model);
    }
}