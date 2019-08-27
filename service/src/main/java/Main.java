import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.service.TransactionService;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        Collection<TransactionModel> models = transactionService.findAll();
        System.out.println(System.getProperty("java.classpath"));
        for (TransactionModel model : models) {
            System.out.println(model.toString());
        }
    }
}
