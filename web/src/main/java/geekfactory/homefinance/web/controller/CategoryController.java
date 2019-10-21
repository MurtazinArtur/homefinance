package geekfactory.homefinance.web.controller;

import geekfactory.homefinance.dao.model.CategoryTransactionModel;
import geekfactory.homefinance.service.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryTransactionService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody CategoryTransactionModel categoryModel, Model model) {
        CategoryTransactionModel saveCategoryModel = new CategoryTransactionModel();

        model.addAttribute("name", saveCategoryModel.getName());
        model.addAttribute("parent_category", saveCategoryModel.getParentCategory().getId());

        return "category_list";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<CategoryTransactionModel> allCategories = categoryTransactionService.findAll();

        model.addAttribute("categories", allCategories);

        return "category_list";
    }

    @PutMapping("/update")
    public String update(@PathVariable String id, @RequestBody CategoryTransactionModel bankModel, Model model) {
        CategoryTransactionModel updateCategoryModel = new CategoryTransactionModel();
        model.addAttribute("id", updateCategoryModel.getId());
        model.addAttribute("name", updateCategoryModel.getName());
        model.addAttribute("parent_category", updateCategoryModel.getParentCategory().getId());

        return "category_list";
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {

        //categoryTransactionService.remove(categoryTransactionService.findById(id));
    }
}