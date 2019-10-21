package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
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
@Repository("categoryRepository")
public class CategoryTransactionRepositoryCRUD {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<CategoryTransactionModel> findByName(String name) {
        TypedQuery<CategoryTransactionModel> query =
                entityManager.createQuery("SELECT category FROM CategoryTransactionModel category " +
                        "WHERE category.name = :name", CategoryTransactionModel.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional(readOnly = true)
    public Optional<CategoryTransactionModel> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryTransactionModel.class, id));
    }

    @Transactional(readOnly = true)
    public Collection<CategoryTransactionModel> findAll() {
        return (Collection<CategoryTransactionModel>) entityManager.createQuery("SELECT category FROM CategoryTransactionModel category").getResultList();
    }

    @Transactional
    public void remove(CategoryTransactionModel model) {
        CategoryTransactionModel modelToDelete = entityManager.find(CategoryTransactionModel.class, model.getId());
        entityManager.remove(modelToDelete);
    }

    @Transactional
    public void save(CategoryTransactionModel model) {
        entityManager.persist(model);
    }

    @Transactional
    public CategoryTransactionModel update(CategoryTransactionModel model) {
        entityManager.merge(model);
        return model;
    }
}