package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSupplier {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/home_finance?useSSL=false&serverTimezone=UTC";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "12WARrior";

    public Connection getConnection() throws HomeFinanceDaoException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error connection on DataBase", e);
        }
    }
}
