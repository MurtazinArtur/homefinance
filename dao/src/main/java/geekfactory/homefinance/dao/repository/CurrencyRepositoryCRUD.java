package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CurrencyModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Transactional
@Repository("currencyRepository")
public class CurrencyRepositoryCRUD implements RepositoryCRUD<CurrencyModel, Long> {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<CurrencyModel> findByName(String name) {
        TypedQuery<CurrencyModel> query =
                entityManager.createQuery("SELECT currency FROM CurrencyModel currency " +
                        "WHERE currency.name = :name", CurrencyModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CurrencyModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CurrencyModel.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CurrencyModel> findAll() {
        return (Collection<CurrencyModel>) entityManager.createQuery("SELECT currency FROM CurrencyModel currency").getResultList();
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        CurrencyModel modelToDelete = entityManager.find(CurrencyModel.class, id);
        entityManager.remove(modelToDelete);
        return true;
    }

    @Transactional
    @Override
    public void save(CurrencyModel model) {
        entityManager.persist(model);
    }

    @Transactional
    @Override
    public void update(CurrencyModel model) {
        entityManager.merge(model);
    }
}