package geekfactory.homefinance.web.servlet;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/category_list")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<CategoryTransactionModel> models = (Set<CategoryTransactionModel>) categoryService.findAll();
        req.setAttribute("categories", models);
        req.getRequestDispatcher("/jsp/jstl/category_list.jsp").forward(req, resp);
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
