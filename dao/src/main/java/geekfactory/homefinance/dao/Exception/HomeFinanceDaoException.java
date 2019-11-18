package geekfactory.homefinance.dao.Exception;

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

