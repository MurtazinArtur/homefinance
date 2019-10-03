package geekfactory.homefinance.spring.controller;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save (@RequestBody BankModel bankModel, Model model){
        BankModel saveBankModel= new BankModel();

        model.addAttribute("name", saveBankModel.getName());

        return "save bank success";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        Collection<BankModel> allBanks = bankService.findAll();

        model.addAttribute("banks", allBanks);

        return "all banks";
    }
}
