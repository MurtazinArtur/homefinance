package geekfactory.homefinance.web.controller;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.service.serviceImpl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody AccountModel accountModel, Model model) {
        AccountModel saveAccountModel = new AccountModel();

        model.addAttribute("name", saveAccountModel.getName());
        model.addAttribute("amount", saveAccountModel.getAmount());
        model.addAttribute("currency", saveAccountModel.getCurrencyModel().getId());
        model.addAttribute("account_type", saveAccountModel.getAccountType());

        return "account_list";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<AccountModel> allAccounts = accountService.findAll();

        model.addAttribute("accounts", allAccounts);

        return "account_list";
    }

    @PutMapping("/update")
    public String update(@PathVariable String id, @RequestBody AccountModel bankModel, Model model) {
        AccountModel updateAccountModel = new AccountModel();
        model.addAttribute("id", updateAccountModel.getId());
        model.addAttribute("name", updateAccountModel.getName());
        model.addAttribute("amount", updateAccountModel.getAmount());
        model.addAttribute("currency", updateAccountModel.getCurrencyModel().getId());
        model.addAttribute("account_type", updateAccountModel.getAccountType());

        return "account_list";
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {

        //accountService.remove(id);
    }
}
