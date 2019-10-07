package geekfactory.homefinance.spring.controller;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.ServiceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private ServiceCRUD<BankModel, Long> bankService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save (@RequestBody BankModel bankModel, Model model){
        BankModel saveBankModel= new BankModel();

        model.addAttribute("name", saveBankModel.getName());

        return "bank_list";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        Collection<BankModel> allBanks = bankService.findAll();

        model.addAttribute("banks", allBanks);

        return "bank_list";
    }

    @PutMapping("/update")
    public String update(@PathVariable String id, @RequestBody BankModel bankModel, Model model) {
        BankModel updateBankModel = new BankModel();
        model.addAttribute("id", updateBankModel.getId());
        model.addAttribute("name", updateBankModel.getName());

        return "banks/bank_list";
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        bankService.remove(id);
    }
}
