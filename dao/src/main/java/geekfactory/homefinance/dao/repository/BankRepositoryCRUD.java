package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.BankModel;
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
@Repository("bankRepository")
public class BankRepositoryCRUD {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<BankModel> findByName(String name) {
        TypedQuery<BankModel> query =
                entityManager.createQuery("SELECT bank FROM BankModel bank " +
                        "WHERE bank.name = :name", BankModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<BankModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<BankModel> findAll() {
        return (Collection<BankModel>) entityManager.createQuery("SELECT bank FROM BankModel bank").getResultList();
    }

    @Transactional()
    public void remove(BankModel model) {
        BankModel modelToDelete = findById(model.getId()).get();
        entityManager.remove(modelToDelete);
    }

    @Transactional
    public void save(BankModel model) {
        entityManager.persist(model);
    }

    @Transactional
    public BankModel update(BankModel model) {
        entityManager.merge(model);
        return model;
    }
}