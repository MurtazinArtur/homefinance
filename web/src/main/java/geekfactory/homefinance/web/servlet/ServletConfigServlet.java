package geekfactory.homefinance.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ServletConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        ServletConfig config = getServletConfig();
        Enumeration<String> parameterNames = config.getInitParameterNames();
        StringBuilder stringBuilder = new StringBuilder();
        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            String paramValue = config.getInitParameter(paramName);
            stringBuilder.append(paramName).append(" = ").append(paramValue).append("\n");
        }
        resp.getOutputStream().write(("<h1>" + stringBuilder.toString() + "</h1>").getBytes());
    }
}
