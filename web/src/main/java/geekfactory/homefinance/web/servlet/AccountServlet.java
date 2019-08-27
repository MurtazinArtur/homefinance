package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountService accountService = new AccountService();
        Collection<AccountModel> models = accountService.findAll();
        for (AccountModel model : models) {
            resp.getOutputStream().write(("<h1>" + model + "<br>" + "</h1>").getBytes());
        }
    }
}
