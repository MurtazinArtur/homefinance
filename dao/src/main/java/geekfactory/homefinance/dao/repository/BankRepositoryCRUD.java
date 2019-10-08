package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.BankModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Transactional
@Repository("bankRepository")
public class BankRepositoryCRUD implements RepositoryCRUD<BankModel, Long> {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<BankModel> findByName(String name) {
        TypedQuery<BankModel> query =
                entityManager.createQuery("SELECT bank FROM BankModel bank " +
                        "WHERE bank.name = :name", BankModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BankModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BankModel.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<BankModel> findAll() {
        return (Collection<BankModel>) entityManager.createQuery("SELECT bank FROM BankModel bank").getResultList();
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        BankModel modelToDelete = entityManager.find(BankModel.class, id);
        entityManager.remove(modelToDelete);
        return true;
    }

    @Transactional
    @Override
    public void save(BankModel model) {
        entityManager.persist(model);
    }

    @Transactional
    @Override
    public void update(BankModel model) {
        entityManager.merge(model);
    }
}