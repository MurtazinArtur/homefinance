package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/currency_list")
public class CurrencyServlet extends HttpServlet {
    private CurrencyService currencyService = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<CurrencyModel> models = currencyService.findAll();
        req.setAttribute("currencies", models);
        req.getRequestDispatcher("jsp/jstl/currency_list.jsp").forward(req, resp);
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
