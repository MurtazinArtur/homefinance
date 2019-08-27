package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class transactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TransactionService transactionService = new TransactionService();
        Collection<TransactionModel> models = transactionService.findAll();
        for (TransactionModel model : models) {
            resp.getOutputStream().write(("<h1>" + model + "<br>" + "</h1>").getBytes());
        }
    }
}