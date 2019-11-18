package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.TransactionModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@EnableTransactionManagement
@Transactional
@Repository("transactionRepository")
public class TransactionRepositoryCRUD {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<TransactionModel> findByName(String name) {
        TypedQuery<TransactionModel> query =
                entityManager.createQuery("SELECT transaction FROM TransactionModel transaction " +
                        "WHERE transaction.name = :name", TransactionModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<TransactionModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(TransactionModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<TransactionModel> findAll() {
        return (Collection<TransactionModel>) entityManager.createQuery("SELECT transaction FROM TransactionModel transaction").getResultList();
    }

    @Transactional
    public void remove(TransactionModel model) {
        TransactionModel modelToDelete = entityManager.find(TransactionModel.class, model.getId());
        entityManager.remove(modelToDelete);
    }

    @Transactional
    public void save(TransactionModel model) {
        entityManager.persist(model);
    }

    @Transactional
    public TransactionModel update(TransactionModel model) {
        entityManager.merge(model);
        return model;
    }
}