package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class BankServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankService bankService = new BankService();
        Collection<BankModel> models = bankService.findAll();
        for (BankModel model : models) {
            resp.getOutputStream().write(("<h1>" + model + "<br>" + "</h1>").getBytes());
        }
    }
}
