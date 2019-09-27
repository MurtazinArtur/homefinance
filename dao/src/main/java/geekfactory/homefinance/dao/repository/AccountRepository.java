package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.dao.model.CurrencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Transactional
public class AccountRepository implements Repository<AccountModel, Long> {
    private final static String INSERT = "INSERT INTO account_tbl(name, amount, currency_id, account_type) VALUES (?, ?, ?, ?)";
    private final static String FIND_BY_ID = "SELECT id, name, amount, currency_id, account_type FROM account_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name, amount, currency_id, account_type FROM account_tbl";
    private final static String REMOVE = "DELETE FROM account_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE account_tbl SET name = ?, amount = ?, currency_id = ?, account_type = ? WHERE id = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Repository<CurrencyModel, Long> currencyModelRepository;

    @Override
    public Optional<AccountModel> findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    AccountModel model = new AccountModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    model.setAmount(resultSet.getBigDecimal(3));
                    Optional<CurrencyModel> currencyModel = currencyModelRepository.findById(resultSet.getLong(4));
                    model.setCurrencyModel(currencyModel.get());
                    model.setAccountType(AccountType.valueOf(resultSet.getString(5)));
                    return Optional.of(model);
                }
        } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
            }
        return Optional.empty();
    }

    @Override
    public Collection<AccountModel> findAll() {
        Collection<AccountModel> listCategory = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Optional<CurrencyModel> currencyModel = currencyModelRepository.findById(resultSet.getLong(4));
                    listCategory.add(new AccountModel(resultSet.getLong(1),
                            currencyModel.get(), resultSet.getString(2),
                            AccountType.valueOf(resultSet.getString(5)),
                            resultSet.getBigDecimal(3)));
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
            }
        return listCategory;
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
    public void save(AccountModel model) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setBigDecimal(2, model.getAmount());
                preparedStatement.setLong(3, model.getCurrencyModel().getId().intValue());
                preparedStatement.setString(4, String.valueOf(model.getAccountType()));
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    model.setId(resultSet.getLong(1));
                }
                connection.commit();

        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error save " + model, e);
        }
    }

    @Override
    public void update(AccountModel model, Long idRow) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                model.setId(idRow);
                preparedStatement.setString(1, model.getName());
                preparedStatement.setBigDecimal(2, model.getAmount());
                preparedStatement.setLong(3, model.getCurrencyModel().getId());
                preparedStatement.setString(4, String.valueOf(model.getAccountType()));
                preparedStatement.setLong(5, idRow);
                preparedStatement.executeUpdate();
                connection.commit();

        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error update " + model, e);
        }
    }
}