package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public class CategoryService implements ServiceCRUD<CategoryTransactionModel, Long> {

    @Autowired
    private Repository<CategoryTransactionModel, Long> categoryTransactionRepository;

    @Override
    public Optional<CategoryTransactionModel> findById(Long id) {
        return categoryTransactionRepository.findById(id);
    }

    @Override
    public Collection<CategoryTransactionModel> findAll() {
        return categoryTransactionRepository.findAll();
    }

    @Override
    public boolean remove(Long id) {
        categoryTransactionRepository.remove(id);
        return true;
    }

    @Override
    public void save(CategoryTransactionModel model) {
        categoryTransactionRepository.save(model);
    }

    @Override
    public void update(CategoryTransactionModel model, Long idRow) {
        categoryTransactionRepository.update(model, idRow);
    }
}
