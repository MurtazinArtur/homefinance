package geekfactory.homefinance.web.servlet.thymeleaf.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThymeleafInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        TemplateEngine engine = templateEngine(sce.getServletContext());
        TemplateEngineUtil.storeTemplateEngine(sce.getServletContext(), engine);
    }

    private TemplateEngine templateEngine(ServletContext servletContext){
        TemplateEngine engine = new TemplateEngine();

        engine.setTemplateResolver(templateResolver(servletContext));

        return engine;
    }

    private ITemplateResolver templateResolver(ServletContext servletContext){
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
        resolver.setPrefix("/resources/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }

    public void contextDestroyed(ServletContextEvent sce){

    }
}
