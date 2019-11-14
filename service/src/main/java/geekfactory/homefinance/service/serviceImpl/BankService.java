package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.BankRepositoryCRUD;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.BankModelConverter;
import geekfactory.homefinance.service.dto.BankDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("bankService")
public class BankService {
    private BankModelConverter converter;
    private TransactionService transactionService;
    private BankRepositoryCRUD bankRepositoryCRUD;

    @Autowired
    public BankService(BankModelConverter converter, TransactionService transactionService,
                       BankRepositoryCRUD bankRepositoryCRUD) {
        this.converter = converter;
        this.transactionService = transactionService;
        this.bankRepositoryCRUD = bankRepositoryCRUD;
    }

    public Optional<BankDtoModel> findById(Long id) {
        return Optional.ofNullable(converter.convertToBankDtoModel(bankRepositoryCRUD.findById(id).get()));
    }

    public Optional<BankDtoModel> findByName(String name) {
        return Optional.ofNullable(converter.convertToBankDtoModel(bankRepositoryCRUD.findByName(name).get()));
    }

    public Collection<BankDtoModel> findAll() {
        return converter.convertCollectionToBankDtoModel(bankRepositoryCRUD.findAll());
    }

    public void save(BankDtoModel model) {
        bankRepositoryCRUD.save(converter.convertToBankModel(model));
    }

    public BankDtoModel update(BankDtoModel model) {
        bankRepositoryCRUD.update(converter.convertToBankModel(model));

        return model;
    }

    public void remove(BankDtoModel bankDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (converter.convertToBankModel(bankDtoModel).equals(transactionModel.getBank())) {
                transactionModel.setBank(null);
                transactionService.update(transactionModel);
            }
        }
        bankRepositoryCRUD.remove(converter.convertToBankModel(bankDtoModel));
    }
}