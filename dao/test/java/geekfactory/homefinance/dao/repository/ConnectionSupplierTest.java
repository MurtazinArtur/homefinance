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

public class ConnectionSupplierTest {

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

    public Connection connectDb() throws IOException, SQLException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("C:\\Users\\Work\\IdeaProjects\\GeekFactory_Web04_Murtazin\\dao\\test\\resources\\dbConnectionProperties"))) {
            props.load(in);
        }
        String url = props.getProperty("dburl");
        String username = props.getProperty("dbuser");
        String password = props.getProperty("dbpassword");

        return DriverManager.getConnection(url, username, password);
    }
}

