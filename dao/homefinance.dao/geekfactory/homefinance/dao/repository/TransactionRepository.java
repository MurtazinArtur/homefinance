package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.TransactionModel;

import java.util.Collection;
import java.util.Optional;

public class TransactionRepository implements Repository <TransactionModel>{
    private final static String INSERT = "INSERT INTO transaction_type_tbl(name) VALUES (?)";
    private final static String FIND_BY_ID = "SELECT id, name FROM transaction_type_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name FROM transaction_type_tbl";
    private final static String REMOVE = "DELETE FROM transaction_type_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE transaction_type_tbl set name = ? WHERE id = ?";
    private ConnectionSupplier connectionSupplier = new ConnectionSupplier();

    @Override
    public Optional<TransactionModel> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Collection<TransactionModel> findAll() {
        return null;
    }

    @Override
    public boolean remove(long id) {
        return false;
    }

    @Override
    public void save(TransactionModel model) {

    }

    @Override
    public void update(TransactionModel model, long idRow) {

    }
}
