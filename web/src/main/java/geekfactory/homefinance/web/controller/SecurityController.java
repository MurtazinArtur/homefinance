package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityController {

    private UserController userController;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public SecurityController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping(value = {"/hello", "/"})
    public String welcome(Model model, @AuthenticationPrincipal User user,
                          @RequestParam(name = "error", required = false) Boolean error) {
        model.addAttribute("authUser", user);
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }

        return "/hello";
    }
}