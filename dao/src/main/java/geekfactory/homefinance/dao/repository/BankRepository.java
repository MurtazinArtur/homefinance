package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.BankModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class BankRepository implements Repository<BankModel>{
    private final static String INIT_DB = "C:\\Users\\Work\\IdeaProjects\\GeekFactory_Web04_Murtazin\\resources\\dbConnectionProperties";
    private final static String INSERT = "INSERT INTO bank_tbl(name) VALUES (?)";
    private final static String FIND_BY_ID = "SELECT id, name FROM bank_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name FROM bank_tbl";
    private final static String REMOVE = "DELETE FROM bank_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE bank_tbl set name = ? WHERE id = ?";
    private ConnectionSupplier connectionSupplier = new ConnectionSupplier();

    @Override
    public Optional<BankModel> findById(Long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    BankModel model = new BankModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    return Optional.of(model);
                }

            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<BankModel> findAll() {
        Collection<BankModel> listCategory = new ArrayList<>();
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    listCategory.add(new BankModel(resultSet.getLong(1), resultSet.getString(2)));
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
        }
        return listCategory;
    }

    @Override
    public boolean remove(Long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                return true;

            } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error delete", e);
        }
    }

    @Override
    public void save(BankModel model) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, model.getName());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    model.setId(resultSet.getLong(1));
                }
                connection.commit();
            } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error save " + model, e);
        }
    }

    @Override
    public void update(BankModel model, Long idRow) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setLong(2, idRow);
                preparedStatement.executeUpdate();
                connection.commit();

        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error update " + model, e);
        }
    }
}