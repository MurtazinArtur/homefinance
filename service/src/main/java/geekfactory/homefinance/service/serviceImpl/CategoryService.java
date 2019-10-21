package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("categoryService")
public class CategoryService {

    @Autowired
    private CategoryTransactionRepositoryCRUD categoryTransactionRepositoryCRUD;

    public Optional<CategoryTransactionModel> findById(Long id) {
        return Optional.ofNullable(categoryTransactionRepositoryCRUD.findById(id).get());
    }

    public Optional<CategoryTransactionModel> findByName(String name) {
        return null;
    }

    public Collection<CategoryTransactionModel> findAll() {
        return categoryTransactionRepositoryCRUD.findAll();
    }

    public void remove(CategoryTransactionModel model) {
        categoryTransactionRepositoryCRUD.remove(model);
    }

    public void save(CategoryTransactionModel model) {
        categoryTransactionRepositoryCRUD.save(model);
    }

    public CategoryTransactionModel update(CategoryTransactionModel model) {
        categoryTransactionRepositoryCRUD.update(model);
        return model;
    }
}