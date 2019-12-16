package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.service.dto.CategoryDtoModel;
import geekfactory.homefinance.service.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryTransactionService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CategoryController(CategoryService categoryTransactionService) {
        this.categoryTransactionService = categoryTransactionService;
    }

    @GetMapping(value = "/add_new_category")
    public String addNewCurrencyPage(Model model) {
        Collection<CategoryDtoModel> allCategories = categoryTransactionService.findAll();
        Set<String> allNamesCategory = new HashSet<>();

        for (CategoryDtoModel categoryDtoModel : allCategories) {
            allNamesCategory.add(categoryDtoModel.getName());
        }
        model.addAttribute("nameCategories", allNamesCategory);

        return "/categories/add_new_category";
    }

    @GetMapping(value = "/category_edit")
    public String editCurrencyPage(Model model) {
        Collection<CategoryDtoModel> allCategories = categoryTransactionService.findAll();
        Set<String> allNamesCategory = new HashSet<>();

        for (CategoryDtoModel categoryDtoModel : allCategories) {
            allNamesCategory.add(categoryDtoModel.getName());
        }
        model.addAttribute("nameCategories", allNamesCategory);

        return "/categories/category_edit";
    }

    @GetMapping("/")
    public String findAll(Model model, @AuthenticationPrincipal User user) {
        Collection<CategoryDtoModel> allCategories = categoryTransactionService.findAll();

        model.addAttribute("categories", allCategories);
        model.addAttribute("authUser", user);

        return "categories/category_list";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String findById(@PathVariable Long id) {
        categoryTransactionService.findById(id).get();

        return "/find";
    }

    @GetMapping("/{name}")
    public @ResponseBody
    String findByName(@PathVariable String name) {
        categoryTransactionService.findByName(name).get();

        return "/findByName";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonCategoryDtoModel) {
        CategoryDtoModel saveCategoryDtoModel = new CategoryDtoModel();

        try {
            saveCategoryDtoModel = mapper.readValue(jsonCategoryDtoModel, CategoryDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        categoryTransactionService.save(saveCategoryDtoModel);

        return new ModelAndView("redirect:/categories/");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String update(@RequestBody String jsonCategoryDtoModel) {
        CategoryDtoModel updateCategoryDtoModel = new CategoryDtoModel();

        try {
            updateCategoryDtoModel = mapper.readValue(jsonCategoryDtoModel, CategoryDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        categoryTransactionService.update(updateCategoryDtoModel);

        return "/categories/";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) String categoryId) {
        CategoryDtoModel removeCategoryDtoModel = categoryTransactionService.findById(Long.valueOf(categoryId)).get();

        categoryTransactionService.remove(removeCategoryDtoModel);

        return "redirect:/categories/";
    }
}