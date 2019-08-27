package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CurrencyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyService currencyService = new CurrencyService();
        Collection<CurrencyModel> models = currencyService.findAll();
        for (CurrencyModel model : models) {
            resp.getOutputStream().write(("<h1>" + model + "<br>" + "</h1>").getBytes());
        }
    }
}
