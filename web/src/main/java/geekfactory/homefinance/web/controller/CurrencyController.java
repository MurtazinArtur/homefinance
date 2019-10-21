package geekfactory.homefinance.web.controller;

import geekfactory.homefinance.dao.model.CurrencyModel;
import geekfactory.homefinance.service.serviceImpl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyServiceCRUD;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody CurrencyModel currencyModel, Model model) {
        CurrencyModel saveCurrencyModel = new CurrencyModel();

        model.addAttribute("name", saveCurrencyModel.getName());
        model.addAttribute("code", saveCurrencyModel.getCode());
        model.addAttribute("symbol", saveCurrencyModel.getSymbol());

        return "currency_list";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<CurrencyModel> allCurrencies = currencyServiceCRUD.findAll();

        model.addAttribute("currencies", allCurrencies);

        return "currency_list";
    }

    @PutMapping("/update")
    public String update(@PathVariable String id, @RequestBody CurrencyModel currencyModel, Model model) {
        CurrencyModel updateCurrencyModel1 = new CurrencyModel();
        model.addAttribute("id", updateCurrencyModel1.getId());
        model.addAttribute("name", updateCurrencyModel1.getName());
        model.addAttribute("code", updateCurrencyModel1.getCode());
        model.addAttribute("symbol", updateCurrencyModel1.getSymbol());

        return "currency_list";
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {

        //currencyServiceCRUD.remove(id);
    }
}