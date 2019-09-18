package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.ResourceBundle;

@WebServlet("/bank_list")
public class BankServlet extends HttpServlet {
    private static final ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");
    private final BankService bankService = new BankService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<BankModel> models = bankService.findAll();
        req.setAttribute("banks", models);
        req.getRequestDispatcher("/jsp/jstl/bank_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankModel bankModel = new BankModel();
        bankModel.setName(req.getParameter("bank_name"));
        if (bankModel.getName() != null && !(bankModel.getName().equals("")) && req.getParameter("bank_id") == null) {
            bankService.save(bankModel);
        }
        if (req.getParameter("bank_id") != null && !(req.getParameter("bank_id").equals(""))) {
            this.doDelete(req, resp);
        }
        if(bankModel != null && req.getParameter("bank_id") != null){
            this.doPut(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankModel bankModel = new BankModel();
        bankModel.setName(req.getParameter("bank_name"));
        bankService.update(bankModel, Long.parseLong(req.getParameter("bank_id")));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bankService.remove(Long.parseLong(req.getParameter("bank_id")));
    }
}
