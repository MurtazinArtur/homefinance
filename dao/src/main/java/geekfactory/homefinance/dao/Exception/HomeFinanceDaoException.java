package geekfactory.homefinance.dao.Exception;

import java.sql.SQLException;

public class HomeFinanceDaoException extends RuntimeException {

    public HomeFinanceDaoException(String message) {
        super(message);
    }

    public HomeFinanceDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public HomeFinanceDaoException(Throwable cause) {
        super(cause);
    }
}

