package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.service.dto.*;
import geekfactory.homefinance.service.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/transactions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionController {
    private TransactionService transactionService;
    private AccountService accountService;
    private CurrencyService currencyService;
    private BankService bankService;
    private CategoryService categoryService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService,
                                 CurrencyService currencyService, BankService bankService,
                                 CategoryService categoryService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.bankService = bankService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/add_new_transaction")
    public String addNewBankPage(Model model) {
        Collection<CurrencyDtoModel> allCurrencies = currencyService.findAll();
        Collection<CategoryDtoModel> allCategories = categoryService.findAll();
        Collection<AccountDtoModel> allAccounts = accountService.findAll();
        Collection<BankDtoModel> allBanks = bankService.findAll();

        model.addAttribute("currencies", allCurrencies);
        model.addAttribute("categories", allCategories);
        model.addAttribute("accounts", allAccounts);
        model.addAttribute("banks", allBanks);

        return "/transactions/add_new_transaction";
    }

    @GetMapping(value = "/transaction_edit")
    public String editBankPage(Model model) {
        Collection<CurrencyDtoModel> allCurrencies = currencyService.findAll();
        Collection<CategoryDtoModel> allCategories = categoryService.findAll();
        Collection<AccountDtoModel> allAccounts = accountService.findAll();
        Collection<BankDtoModel> allBanks = bankService.findAll();

        model.addAttribute("currencies", allCurrencies);
        model.addAttribute("categories", allCategories);
        model.addAttribute("accounts", allAccounts);
        model.addAttribute("banks", allBanks);
        return "/transactions/transaction_edit";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonTransactionDtoModel) {
        TransactionDtoModel transactionDtoModel = new TransactionDtoModel();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            transactionDtoModel = mapper.readValue(jsonTransactionDtoModel, TransactionDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        transactionService.save(transactionDtoModel);

        return new ModelAndView("redirect:/transactions/");
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<TransactionModel> allTransactions = transactionService.findAll();

        model.addAttribute("transactions", allTransactions);

        return "transactions/transaction_list";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView update(@RequestBody String jsonTransactionDtoModel) {
        TransactionDtoModel updateTransactionDtoModel = new TransactionDtoModel();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            updateTransactionDtoModel = mapper.readValue(jsonTransactionDtoModel, TransactionDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        transactionService.update(updateTransactionDtoModel);

        return new ModelAndView("redirect:/transactions/");
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) String transactionId) {
        TransactionDtoModel removedTransactionDtoModel = transactionService.findById(Long.valueOf(transactionId)).get();
        transactionService.remove(removedTransactionDtoModel);

        return "redirect:/transactions/";
    }
}
