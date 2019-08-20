package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class CategoryTransactionRepository implements Repository<CategoryTransactionModel> {
    private final static String INIT_DB = "C:\\Users\\Work\\IdeaProjects\\GeekFactory_Web04_Murtazin\\resources\\dbConnectionProperties.properties.properties";
    private final static String INSERT = "INSERT INTO category_tbl(name, parent_category) VALUES (?, ?)";
    private final static String FIND_BY_ID = "SELECT id, name, parent_category FROM category_tbl WHERE id = ?";
    private final static String FIND_ALL = "SELECT id, name, parent_category FROM category_tbl";
    private final static String REMOVE = "DELETE FROM category_tbl WHERE id = ?";
    private final static String UPDATE = "UPDATE category_tbl set name = ?, parent_category = ? WHERE id = ?";
    private ConnectionSupplier connectionSupplier = new ConnectionSupplier();

    @Override
    public Optional<CategoryTransactionModel> findById(Long id) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    CategoryTransactionModel model = new CategoryTransactionModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    if (resultSet.getString(3) != null) {
                        Optional<CategoryTransactionModel> parentCategory = findById(resultSet.getLong(3));
                        model.setParentCategory(parentCategory.get());
                    }else {
                       model.setParentCategory(null);
                    }
                    return Optional.of(model);
                }

            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find " + id, e);
        }
        return Optional.empty();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Collection<CategoryTransactionModel> findAll() {
        Collection<CategoryTransactionModel> hashSetCategory = new HashSet<>();
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CategoryTransactionModel model = new CategoryTransactionModel();
                    model.setId(resultSet.getLong(1));
                    model.setName(resultSet.getString(2));
                    if (resultSet.getString(3) != null) {
                        model.setParentCategory(findById(resultSet.getLong(3)).orElse(null));
                    }
                    hashSetCategory.add(model);
                }
            } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error find", e);
        }
        return hashSetCategory;
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
    public void save(CategoryTransactionModel model) {
        try {
            Connection connection = connectionSupplier.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, model.getName());
                if (model.getParentCategory() != null) {
                    preparedStatement.setLong(2, model.getParentCategory().getId());
                }else{
                    preparedStatement.setNull(2, Types.INTEGER);
                }
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
    public void update(CategoryTransactionModel model, Long idRow) {
        if (model != null) {
            try {
                Connection connection = connectionSupplier.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
                    preparedStatement.setString(1, model.getName());
                    preparedStatement.setLong(3, idRow);
                    if (model.getParentCategory() != null) {
                        preparedStatement.setLong(2, model.getParentCategory().getId());
                    } else {
                        preparedStatement.setString(2, null);
                    }
                    preparedStatement.executeUpdate();
                    connection.commit();

                } catch (SQLException e) {
                throw new HomeFinanceDaoException("Error update " + model, e);
            }
        }
    }
}