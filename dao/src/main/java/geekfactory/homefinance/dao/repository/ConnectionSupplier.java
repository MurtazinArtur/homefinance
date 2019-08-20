package geekfactory.homefinance.dao.repository;

import geekfactory.homefinance.dao.Exception.HomeFinanceDaoException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSupplier {
    private Connection connection = null;

    public Connection getConnection() throws HomeFinanceDaoException {
        try {
            try (InputStream is = this.getClass().getResourceAsStream("/dbConnectionProperties.properties")) {
                Properties properties = new Properties();
                properties.load(is);
                String url = properties.getProperty("dburl");
                String user = properties.getProperty("dbuser");
                String password = properties.getProperty("dbpassword");

                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert connection != null;
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new HomeFinanceDaoException(e);
        }
        return connection;
    }
    public void getDisconnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new HomeFinanceDaoException(e);
        }
    }
}
