package geekfactory.homefinance.web.controller;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.serviceImpl.BankService;
import geekfactory.homefinance.web.dto.BankDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/add_new_bank", method = RequestMethod.GET)
    public String addNewBankPage() {
        return "/banks/add_new_bank";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        Collection<BankModel> allBanks = bankService.findAll();
        model.addAttribute("banks", allBanks);

        return "/banks/bank_list";
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String findById(@PathVariable Long id) {
        bankService.findById(id).get();
        return "/find";
    }

    @GetMapping(value = "/banks/add_new_bank")
    public String save(Model model) {
        model.addAttribute("bank", new BankModel());
        return "/banks/add_new_bank";
    }

    @PostMapping(value = "/banks/add_new_bank")
    public String save(@ModelAttribute("bank") BankModel bankModel) {
        BankModel saveBankModel = new BankModel();
        saveBankModel.setName(bankModel.getName());

        bankService.save(saveBankModel);

        return "redirect:/banks/";
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String update(@RequestBody BankDtoModel bankDtoModel) {
        BankModel updateBankModel = new BankModel();
        updateBankModel.setId(bankDtoModel.getId());
        updateBankModel.setName(bankDtoModel.getName());

        bankService.update(updateBankModel);

        return "banks/bank_list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id", required = true) String bankId) {
        bankService.remove(bankService.findById(Long.valueOf(bankId)).get());
        return "redirect:/banks/";
    }
}
