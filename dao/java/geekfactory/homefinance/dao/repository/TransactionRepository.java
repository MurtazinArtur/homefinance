package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;
import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.model.TransactionModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class TransactionRepository implements Repository <TransactionModel>{
    private final static String INSERT = "INSERT INTO transaction_tbl(amount, date, source, bank_id, account_id, currency_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String INSERT_TRANSACTION_CATEGORY = "INSERT INTO transaction_category_tbl(transaction_id, category_id) VALUES (?, ?)";
    private final static String FIND_BY_ID = "SELECT id, amount, date, source, bank_id, account_id, currency_id FROM transaction_tbl WHERE id = ?";
    private final static String FIND_CATEGORY_BY_ID = "SELECT transaction_id, category_id FROM transaction_category_tbl WHERE transaction_id = ?";
    private final static String FIND_ALL = "SELECT id, amount, date, source, bank_id, account_id, currency_id FROM transaction_tbl";
    private final static String REMOVE = "DELETE FROM transaction_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE transaction_tbl set amount = ?, date = ?, source = ?,bank_id = ?, account_id = ?, currency_id = ?   WHERE id = ?";
    private ConnectionSupplier connectionSupplier = new ConnectionSupplier();

    @Override
    public Optional<TransactionModel> findById(Long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return Optional.of(createModel(resultSet));
                }

            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
            }
        } catch (HomeFinanceDaoException e) {
            throw new HomeFinanceDaoException("Error find " + id, e);
        }
        return Optional.empty();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Collection<TransactionModel> findAll() {
        Collection<TransactionModel> listTransaction = new ArrayList<>();
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    listTransaction.add(createModel(resultSet));
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
            }
        } catch (HomeFinanceDaoException e) {
            throw new HomeFinanceDaoException("Error find", e);
        }
        return listTransaction;
    }

    @Override
    public boolean remove(Long id) {
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
    public void save(TransactionModel model) {
        try {
            Connection connection = connectionSupplier.getConnection();
            Collection<CategoryTransactionModel> heshSetCategory = new HashSet<>();
            try {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setBigDecimal(1, model.getAmount());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setString(3, model.getSource());
                if (model.getBank() != null) {
                    preparedStatement.setLong(4, model.getBank().getId());
                } else {
                    System.out.println("Поле Банк не может быть пустым!");
                }if (model.getAccount() != null) {
                    preparedStatement.setLong(5, model.getAccount().getId());
                } else {
                    System.out.println("Поле Платеж не может быть пустым!");
                }if (model.getCurrency() != null) {
                    preparedStatement.setLong(6, model.getCurrency().getId());
                } else {
                    System.out.println("Поле Платеж не может быть пустым!");
                }

                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    model.setId(resultSet.getLong(1));
                }
                connection.commit();
                PreparedStatement putPreparedStatement = connection.prepareStatement(INSERT_TRANSACTION_CATEGORY);
                for (CategoryTransactionModel setCategory : model.getCategory()) {
                    heshSetCategory.add(putCategory(connection, putPreparedStatement, model.getId(), setCategory.getId()));
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new HomeFinanceDaoException("Error save " + model, e);
            }
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error save " + model, e);
        }
    }

    @Override
    public void update(TransactionModel model, Long idRow) {
        if (model != null) {
            try {
                Connection connection = connectionSupplier.getConnection();
                try {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(UPDATE);
                    preparedStatement.setLong(6, idRow);

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

    private TransactionModel createModel(ResultSet resultSet) throws SQLException {
        BankRepository bankRepository = new BankRepository();
        AccountRepository accountRepository = new AccountRepository();
        TransactionModel model = new TransactionModel();

        model.setId(resultSet.getLong(1));
        model.setAmount(resultSet.getBigDecimal(2));
        model.setDate(resultSet.getTimestamp(3).toLocalDateTime());
        model.setSource(resultSet.getString(4));
        model.setCategory(getCategories(resultSet.getLong(5)));
        model.setBank((bankRepository.findById
                (resultSet.getLong(6)).get()));
        model.setAccount((accountRepository.findById
                (resultSet.getLong(7)).get()));
        return model;
    }

    private Collection<CategoryTransactionModel> getCategories(Long transactionId) {
        HashSet<CategoryTransactionModel> categoryTransactionHashSet = new HashSet<>();
        try {
            Connection connection = connectionSupplier.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_CATEGORY_BY_ID);
                preparedStatement.setLong(1, transactionId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Long idCategory = resultSet.getLong(2);
                    CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
                    Optional<CategoryTransactionModel> model = Optional.ofNullable(categoryTransactionRepository.findById(idCategory).orElse(null));
                    categoryTransactionHashSet.add(model.get());
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
            }
        } catch (HomeFinanceDaoException e) {
            throw new HomeFinanceDaoException("Error find", e);
        }
        return categoryTransactionHashSet;
    }

    private CategoryTransactionModel putCategory(Connection connection, PreparedStatement putPreparedStatement, Long idTransaction, Long idCategory) {
        try {
            putPreparedStatement.setLong(1, idTransaction);
            putPreparedStatement.setLong(2, idCategory);
            putPreparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new HomeFinanceDaoException("Error paste data in table", ex);
            }
            throw new HomeFinanceDaoException("Error paste data in table", e);
        }
        CategoryTransactionRepository categoryTransactionRepository = new CategoryTransactionRepository();
        return new CategoryTransactionModel(categoryTransactionRepository.findById(idCategory));
    }
}
