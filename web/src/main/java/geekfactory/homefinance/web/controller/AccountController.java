package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.AccountType;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import geekfactory.homefinance.service.serviceImpl.AccountService;
import geekfactory.homefinance.service.serviceImpl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private CurrencyService currencyService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AccountController(AccountService accountService, CurrencyService currencyService) {
        this.accountService = accountService;
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/add_new_account")
    public String addNewBankPage(Model model) {
        Collection<CurrencyDtoModel> allCurrencies = currencyService.findAll();
        AccountType[] allAccountTypes = AccountType.values();
        List<String> valuesAccountTypes = new ArrayList<>();

        for (AccountType accountType : allAccountTypes) {
            valuesAccountTypes.add(accountType.name());
        }

        model.addAttribute("currencies", allCurrencies);
        model.addAttribute("accountTypes", valuesAccountTypes);

        return "/accounts/add_new_account";
    }

    @GetMapping(value = "/account_edit")
    public String editBankPage(Model model) {

        Collection<CurrencyDtoModel> allCurrencies = currencyService.findAll();
        AccountType[] allAccountTypes = AccountType.values();
        List<String> valuesAccountTypes = new ArrayList<>();

        for (AccountType accountType : allAccountTypes) {
            valuesAccountTypes.add(accountType.name());
        }

        model.addAttribute("currencies", allCurrencies);
        model.addAttribute("accountTypes", valuesAccountTypes);

        return "/accounts/account_edit";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonAccountDtoModel) {
        AccountDtoModel accountDtoModel = new AccountDtoModel();

        try {
            accountDtoModel = mapper.readValue(jsonAccountDtoModel, AccountDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        accountService.save(accountDtoModel);

        return new ModelAndView("redirect:/accounts/");
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<AccountDtoModel> allAccounts = accountService.findAll();

        model.addAttribute("accounts", allAccounts);

        return "accounts/account_list";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView update(@RequestBody String jsonAccountDtoModel) {
        AccountDtoModel updateAccountDtoModel = new AccountDtoModel();
        try {
            updateAccountDtoModel = mapper.readValue(jsonAccountDtoModel, AccountDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        accountService.update(updateAccountDtoModel);

        return new ModelAndView("/accounts/");
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) String accountId) {
        AccountDtoModel removedAccountDtoModel = accountService.findById(Long.valueOf(accountId)).get();
        accountService.remove(removedAccountDtoModel);

        return "redirect:/accounts/";
    }
}