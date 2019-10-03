package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.CurrencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Transactional
public class CurrencyRepository implements Repository<CurrencyModel, Long> {
    private final static String INSERT = "INSERT INTO currency_tbl(name, code, symbol) VALUES (?, ?, ?)";
    private final static String FIND_BY_ID = "SELECT id, name, code, symbol FROM currency_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name, code, symbol FROM currency_tbl";
    private final static String REMOVE = "DELETE FROM currency_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE currency_tbl set name = ?, code = ?, symbol = ?  WHERE id = ?";

    @Autowired
    DataSource dataSource;

    @Override
    public Optional<CurrencyModel> findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    CurrencyModel model = new CurrencyModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    model.setCode(resultSet.getString(3));
                    model.setSymbol(resultSet.getString(4));
                    return Optional.of(model);
                }

            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Collection<CurrencyModel> findAll() {
        Collection<CurrencyModel> listCurrency = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    listCurrency.add(new CurrencyModel(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getString(4)));
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
        }
        return listCurrency;
    }

    @Override
    public boolean remove(Long id) {
        try {
            Connection connection = dataSource.getConnection();
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
    public void save(CurrencyModel model) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setString(2, model.getCode());
                preparedStatement.setString(3, model.getSymbol());
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
    public void update(CurrencyModel model, Long idRow) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE);
                model.setId(idRow);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setString(2, model.getCode());
                preparedStatement.setString(3, model.getSymbol());
                preparedStatement.setLong(4, idRow);
                preparedStatement.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error update " + model, e);
        }
    }
}