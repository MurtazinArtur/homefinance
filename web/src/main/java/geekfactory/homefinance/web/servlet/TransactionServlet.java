package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/transaction_list")
public class TransactionServlet extends HttpServlet {
    private final TransactionService transactionService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<TransactionModel> models = transactionService.findAll();
        req.setAttribute("transactions", models);
        req.getRequestDispatcher("/jsp/jstl/transaction_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}