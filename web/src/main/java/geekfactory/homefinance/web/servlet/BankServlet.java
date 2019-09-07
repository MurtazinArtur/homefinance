package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/bank_list")
public class BankServlet extends HttpServlet {
    private final BankService bankService = new BankService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<BankModel> models = bankService.findAll();
        req.setAttribute("banks", models);
        req.getRequestDispatcher("/jsp/jstl/bank_list.jsp").forward(req, resp);
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
