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
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
            assert connection != null;
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new HomeFinanceDaoException("Error connection on DataBase", e);
        }
    }

    public Connection connectDb(String path) throws IOException, SQLException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            props.load(in);
        }
        String url = props.getProperty("dburl");
        String username = props.getProperty("dbuser");
        String password = props.getProperty("dbpassword");

        return DriverManager.getConnection(url, username, password);
    }
    public void getDisconnect(){
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
