package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Transactional
@Repository("transactionRepository")
public class TransactionRepositoryCRUD implements RepositoryCRUD<TransactionModel, Long> {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<TransactionModel> findByName(String name) {
        TypedQuery<TransactionModel> query =
                entityManager.createQuery("SELECT transaction FROM TransactionModel transaction " +
                        "WHERE transaction.name = :name", TransactionModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TransactionModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(TransactionModel.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<TransactionModel> findAll() {
        return (Collection<TransactionModel>) entityManager.createQuery("SELECT transaction FROM TransactionModel transaction").getResultList();
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        TransactionModel modelToDelete = entityManager.find(TransactionModel.class, id);
        entityManager.remove(modelToDelete);
        return true;
    }

    @Transactional
    @Override
    public void save(TransactionModel model) {
        entityManager.persist(model);
    }

    @Transactional
    @Override
    public void update(TransactionModel model) {
        entityManager.merge(model);
    }
}