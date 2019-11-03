package geekfactory.homefinance.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import geekfactory.homefinance.service.dto.BankDtoModel;
import geekfactory.homefinance.service.serviceImpl.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/banks")
public class BankController {

    private BankService bankService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping(value = "/add_new_bank")
    public String addNewBankPage() {
        return "/banks/add_new_bank";
    }


    @GetMapping(value = "/bank_edit")
    public String editBankPage() {
        return "/banks/bank_edit";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<BankDtoModel> allBanks = bankService.findAll();

        model.addAttribute("banks", allBanks);

        return "/banks/bank_list";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String findById(@PathVariable Long id) {
        bankService.findById(id).get();
        return "/find";
    }

    @GetMapping("/{name}")
    public @ResponseBody
    String findByName(@PathVariable String name) {
        bankService.findByName(name).get();
        return "/findByName";
    }

    @PostMapping(value = "/save", consumes =MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView save(@RequestBody String jsonBankDtoModel) {
        BankDtoModel saveBankModel = new BankDtoModel();

        try {
            saveBankModel = mapper.readValue(jsonBankDtoModel, BankDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        bankService.save(saveBankModel);

        return new ModelAndView("redirect:/banks/");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String update(@RequestBody String jsonBankDtoModel) {
        BankDtoModel updateModel = new BankDtoModel();

        try {
            updateModel = mapper.readValue(jsonBankDtoModel, BankDtoModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        bankService.update(updateModel);

        return "/banks/";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id", required = true) String bankId) {
        BankDtoModel removedBankDtoModel = bankService.findById(Long.valueOf(bankId)).get();
        bankService.remove(removedBankDtoModel);
        return "redirect:/banks/";
    }
}
