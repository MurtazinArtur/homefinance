import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.dao.repository.*;
import geekfactory.homefinance.service.TransactionService;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        Collection<TransactionModel> models = transactionService.findAll();
        for (TransactionModel model : models) {
            System.out.println(model.toString());
        }
    }
}
