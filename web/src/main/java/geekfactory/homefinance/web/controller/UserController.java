package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.service.dto.UserDtoModel;
import geekfactory.homefinance.service.dto.UserRoles;
import geekfactory.homefinance.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/add_new_user")
    public String addNewUserPage(Model model) {
        UserRoles[] allUserRoles = UserRoles.values();
        List<String> valuesUserRoles = new ArrayList<>();

        for (UserRoles userRoles : allUserRoles) {
            valuesUserRoles.add(userRoles.name());
        }

        model.addAttribute("userRoles", valuesUserRoles);

        return "/users/add_new_user";
    }


    @GetMapping(value = "/user_edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUserPage(Model model) {

        UserRoles[] allUserRoles = UserRoles.values();
        List<String> valuesUserRoles = new ArrayList<>();

        for (UserRoles userRoles : allUserRoles) {
            valuesUserRoles.add(userRoles.name());
        }

        model.addAttribute("userRoles", valuesUserRoles);

        return "/users/user_edit";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String findAll(Model model, @AuthenticationPrincipal User user) {
        Collection<UserDtoModel> allUsers = userService.findAll();

        model.addAttribute("users", allUsers);
        model.addAttribute("authUser", user);

        return "/users/user_list";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String findById(@PathVariable Long id) {
        userService.findById(id).get();
        return "/find";
    }

    @GetMapping("/{name}")
    public @ResponseBody
    String findByName(@PathVariable String name) {
        userService.findByName(name).get();
        return "/findByName";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonUserDtoModel) {
        UserDtoModel saveUserModel = new UserDtoModel();
        try {
            saveUserModel = mapper.readValue(jsonUserDtoModel, UserDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(saveUserModel.getPassword());
        saveUserModel.setPassword(password);
        userService.save(saveUserModel);

        return new ModelAndView("redirect:/users/");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    String update(@RequestBody String jsonUserDtoModel) {
        UserDtoModel updateUserModel = new UserDtoModel();

        try {
            updateUserModel = mapper.readValue(jsonUserDtoModel, UserDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        userService.update(updateUserModel);

        return "/users/";
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable(value = "id", required = true) String userId) {
        UserDtoModel removedUserDtoModel = userService.findById(Long.valueOf(userId)).get();
        userService.remove(removedUserDtoModel);
        return "redirect:/users/";
    }
}