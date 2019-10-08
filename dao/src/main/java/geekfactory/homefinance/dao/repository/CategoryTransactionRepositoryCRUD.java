package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Transactional
@Repository("categoryRepository")
public class CategoryTransactionRepositoryCRUD implements RepositoryCRUD<CategoryTransactionModel, Long> {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public Optional<CategoryTransactionModel> findByName(String name) {
        TypedQuery<CategoryTransactionModel> query =
                entityManager.createQuery("SELECT category FROM CategoryTransactionModel category " +
                        "WHERE category.name = :name", CategoryTransactionModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CategoryTransactionModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryTransactionModel.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CategoryTransactionModel> findAll() {
        return (Collection<CategoryTransactionModel>) entityManager.createQuery("SELECT category FROM CategoryTransactionModel category").getResultList();
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        CategoryTransactionModel modelToDelete = entityManager.find(CategoryTransactionModel.class, id);
        entityManager.remove(modelToDelete);
        return true;
    }

    @Transactional
    @Override
    public void save(CategoryTransactionModel model) {
        entityManager.persist(model);
    }

    @Transactional
    @Override
    public void update(CategoryTransactionModel model) {
        entityManager.merge(model);
    }
}