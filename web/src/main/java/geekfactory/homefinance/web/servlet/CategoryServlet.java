package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        Collection<CategoryTransactionModel> models = categoryService.findAll();
        for (CategoryTransactionModel model : models) {
            resp.getOutputStream().write(("<h1>" + model + "<br>" + "</h1>").getBytes());
        }
    }
}
