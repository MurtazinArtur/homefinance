package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.CategoryTransactionRepository;

import java.util.Collection;
import java.util.Optional;

public class CategoryService implements Service<CategoryTransactionModel> {
    private CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();

    public CategoryTransactionRepository getCategoryTransactionRepository() {
        return categoryTransactionRepository;
    }

    public void setCategoryTransactionRepository(CategoryTransactionRepository categoryTransactionRepository) {
        this.categoryTransactionRepository = categoryTransactionRepository;
    }

    @Override
    public Optional<CategoryTransactionModel> findById(long id) {
        return categoryTransactionRepository.findById(id);
    }

    @Override
    public Collection<CategoryTransactionModel> findAll() {
        return categoryTransactionRepository.findAll();
    }

    @Override
    public boolean remove(long id) {
        categoryTransactionRepository.remove(id);
        return true;
    }

    @Override
    public void save(CategoryTransactionModel model) {
        categoryTransactionRepository.save(model);
    }

    @Override
    public void update(CategoryTransactionModel model, long idRow) {
        categoryTransactionRepository.update(model, idRow);
    }
}
