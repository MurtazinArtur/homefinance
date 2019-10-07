package geekfactory.homefinance.service;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.repository.RepositoryCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("categoryService")
public class CategoryService implements ServiceCRUD<CategoryTransactionModel, Long> {

    @Autowired
    private RepositoryCRUD<CategoryTransactionModel, Long> categoryTransactionRepositoryCRUD;

    @Override
    public Optional<CategoryTransactionModel> findById(Long id) {
        return categoryTransactionRepositoryCRUD.findById(id);
    }

    @Override
    public Collection<CategoryTransactionModel> findAll() {
        return categoryTransactionRepositoryCRUD.findAll();
    }

    @Override
    public boolean remove(Long id) {
        categoryTransactionRepositoryCRUD.remove(id);
        return true;
    }

    @Override
    public void save(CategoryTransactionModel model) {
        categoryTransactionRepositoryCRUD.save(model);
    }

    @Override
    public void update(CategoryTransactionModel model, Long idRow) {
        categoryTransactionRepositoryCRUD.update(model, idRow);
    }
}
