package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CurrencyModel;
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
@Repository("currencyRepository")
public class CurrencyRepositoryCRUD {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<CurrencyModel> findByName(String name) {
        TypedQuery<CurrencyModel> query =
                entityManager.createQuery("SELECT currency FROM CurrencyModel currency " +
                        "WHERE currency.name = :name", CurrencyModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<CurrencyModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CurrencyModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<CurrencyModel> findAll() {
        return (Collection<CurrencyModel>) entityManager.createQuery("SELECT currency FROM CurrencyModel currency").getResultList();
    }

    @Transactional
    public void remove(CurrencyModel model) {
        CurrencyModel modelToDelete = entityManager.find(CurrencyModel.class, model.getId());
        entityManager.remove(modelToDelete);
    }

    @Transactional
    public void save(CurrencyModel model) {
        entityManager.persist(model);
    }

    @Transactional
    public CurrencyModel update(CurrencyModel model) {
        entityManager.merge(model);
        return model;
    }
}