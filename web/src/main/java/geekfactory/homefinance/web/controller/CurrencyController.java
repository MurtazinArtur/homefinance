package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.service.dto.CurrencyDtoModel;
import geekfactory.homefinance.service.serviceImpl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private CurrencyService currencyService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/add_new_currency")
    public String addNewCurrencyPage() {
        return "/currencies/add_new_currency";
    }


    @GetMapping(value = "/currency_edit")
    public String editCurrencyPage() {
        return "/currencies/currency_edit";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<CurrencyDtoModel> allCurrencies = currencyService.findAll();

        model.addAttribute("currencies", allCurrencies);

        return "/currencies/currency_list";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String findById(@PathVariable Long id) {
        currencyService.findById(id).get();

        return "/find";
    }


    @GetMapping("/{name}")
    public @ResponseBody
    String findByName(@PathVariable String name) {
        currencyService.findByName(name).get();

        return "/findByName";
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonCurrencyDtoModel) {
        CurrencyDtoModel saveCurrencyModel = new CurrencyDtoModel();

        try {
            saveCurrencyModel = mapper.readValue(jsonCurrencyDtoModel, CurrencyDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        currencyService.save(saveCurrencyModel);

        return new ModelAndView("redirect:/currencies/");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView update(@RequestBody String jsonCurrencyDtoModel) {
        CurrencyDtoModel updateCurrencyModel = new CurrencyDtoModel();
        try {
            updateCurrencyModel = mapper.readValue(jsonCurrencyDtoModel, CurrencyDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        currencyService.update(updateCurrencyModel);

        return new ModelAndView("/currencies/");
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) String currencyId) {
        CurrencyDtoModel removeCurrencyDtoModel = currencyService.findById(Long.valueOf(currencyId)).get();

        currencyService.remove(removeCurrencyDtoModel);

        return "redirect:/currencies/";
    }
}