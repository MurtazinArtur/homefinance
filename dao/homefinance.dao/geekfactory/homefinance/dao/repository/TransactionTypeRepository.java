package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.TransactionTypeModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class TransactionTypeRepository implements Repository<TransactionTypeModel>{
    private final static String INSERT = "INSERT INTO transaction_type_tbl(name) VALUES (?)";
    private final static String FIND_BY_ID = "SELECT id, name FROM transaction_type_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name FROM transaction_type_tbl";
    private final static String REMOVE = "DELETE FROM transaction_type_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE transaction_type_tbl set name = ? WHERE id = ?";
    private ConnectionSupplier connectionSupplier = new ConnectionSupplier();

    @Override
    public Optional<TransactionTypeModel> findById(long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    TransactionTypeModel model = new TransactionTypeModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    return Optional.of(model);
                }

            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
            }
        } catch (HomeFinanceDaoException e) {
            throw new HomeFinanceDaoException("Error find " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<TransactionTypeModel> findAll() {
        Collection<TransactionTypeModel> listCategory = new ArrayList<>();
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    listCategory.add(new TransactionTypeModel(resultSet.getLong(1), resultSet.getString(2)));
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
            }
        } catch (HomeFinanceDaoException e) {
            throw new HomeFinanceDaoException("Error find", e);
        }
        return listCategory;
    }

    @Override
    public boolean remove(long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(REMOVE);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();
                throw new HomeFinanceDaoException("Error delete", e);
            }
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error delete ", e);
        }
    }

    @Override
    public void save(TransactionTypeModel model) {
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, model.getName());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    model.setId(resultSet.getLong(1));
                }
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new HomeFinanceDaoException("Error save " + model, e);
            }
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error save " + model, e);
        }
    }

    @Override
    public void update(TransactionTypeModel model, long idRow) {
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(UPDATE);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setLong(2, idRow);
                preparedStatement.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw new HomeFinanceDaoException("Error update " + model, e);
            }
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error update " + model, e);
        }
    }
}
