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

    public Connection getConnection() throws HomeFinanceDaoException {
        try {
            Connection connection = connectDb();
            assert connection != null;
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException | IOException e) {
            throw new HomeFinanceDaoException("Error connection on DataBase", e);
        }
    }

    private Connection connectDb() throws IOException, SQLException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("dbConnectionProperties"))) {
            props.load(in);
        }
        String url = props.getProperty("dburl");
        String username = props.getProperty("dbuser");
        String password = props.getProperty("dbpassword");

        return DriverManager.getConnection(url, username, password);
    }
}
