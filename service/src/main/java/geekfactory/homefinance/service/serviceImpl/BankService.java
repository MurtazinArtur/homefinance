package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.BankRepositoryCRUD;
import geekfactory.homefinance.service.converter.BankModelConverter;
import geekfactory.homefinance.service.converter.TransactionModelConverter;
import geekfactory.homefinance.service.dto.BankDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("bankService")
public class BankService {
    private BankModelConverter bankModelConverter;
    private TransactionModelConverter transactionModelConverter;
    private TransactionService transactionService;
    private BankRepositoryCRUD bankRepositoryCRUD;

    @Autowired
    public BankService(BankModelConverter bankModelConverter, TransactionModelConverter transactionModelConverter,
                       TransactionService transactionService, BankRepositoryCRUD bankRepositoryCRUD) {
        this.bankModelConverter = bankModelConverter;
        this.transactionModelConverter = transactionModelConverter;
        this.transactionService = transactionService;
        this.bankRepositoryCRUD = bankRepositoryCRUD;
    }

    public Optional<BankDtoModel> findById(Long id) {
        return Optional.ofNullable(bankModelConverter.convertToBankDtoModel(bankRepositoryCRUD.findById(id).get()));
    }

    public Optional<BankDtoModel> findByName(String name) {
        return Optional.ofNullable(bankModelConverter.convertToBankDtoModel(bankRepositoryCRUD.findByName(name).get()));
    }

    public Collection<BankDtoModel> findAll() {
        return bankModelConverter.convertCollectionToBankDtoModel(bankRepositoryCRUD.findAll());
    }

    public void save(BankDtoModel model) {
        bankRepositoryCRUD.save(bankModelConverter.convertToBankModel(model));
    }

    public BankDtoModel update(BankDtoModel model) {
        bankRepositoryCRUD.update(bankModelConverter.convertToBankModel(model));

        return model;
    }

    public void remove(BankDtoModel bankDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionService.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (bankModelConverter.convertToBankModel(bankDtoModel).equals(transactionModel.getBank())) {
                transactionModel.setBank(null);
                transactionService.update(transactionModelConverter.convertToTransactionDtoModel(transactionModel));
            }
        }
        bankRepositoryCRUD.remove(bankModelConverter.convertToBankModel(bankDtoModel));
    }
}