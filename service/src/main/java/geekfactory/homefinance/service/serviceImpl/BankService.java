package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.model.BankModel;
import geekfactory.homefinance.dao.model.TransactionModel;
import geekfactory.homefinance.dao.repository.BankRepositoryCRUD;
import geekfactory.homefinance.dao.repository.TransactionRepositoryCRUD;
import geekfactory.homefinance.service.converter.BankModelConverter;
import geekfactory.homefinance.service.dto.BankDtoModel;
import geekfactory.homefinance.service.dto.TransactionDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("bankService")
public class BankService {
    BankModelConverter converter = new BankModelConverter();
    private TransactionRepositoryCRUD transactionRepositoryCRUD;
    private BankRepositoryCRUD bankRepositoryCRUD;

    @Autowired
    public BankService(TransactionRepositoryCRUD transactionRepositoryCRUD, BankRepositoryCRUD bankRepositoryCRUD) {
        this.transactionRepositoryCRUD = transactionRepositoryCRUD;
        this.bankRepositoryCRUD = bankRepositoryCRUD;
    }

    @Transactional
    public Optional<BankDtoModel> findById(Long id) {
        return Optional.ofNullable(converter.convertToBankDtoModel(bankRepositoryCRUD.findById(id).get()));
    }

    public Optional<BankDtoModel> findByName(String name) {
        return Optional.ofNullable(converter.convertToBankDtoModel(bankRepositoryCRUD.findByName(name).get()));
    }

    @Transactional
    public Collection<BankDtoModel> findAll() {
        return converter.convertCollectionToBankDtoModel(bankRepositoryCRUD.findAll());
    }

    @Transactional()
    public void remove(BankDtoModel bankDtoModel) {
        Collection<TransactionModel> transactionModelCollection = transactionRepositoryCRUD.findAll();

        for (TransactionModel transactionModel : transactionModelCollection) {
            if (converter.convertToBankModel(bankDtoModel).equals(transactionModel.getBank())) {
                transactionModel.setBank(null);
                transactionRepositoryCRUD.update(transactionModel);
            }
        }
        bankRepositoryCRUD.remove(converter.convertToBankModel(bankDtoModel));
    }

    @Transactional
    public void save(BankDtoModel model) {
        bankRepositoryCRUD.save(converter.convertToBankModel(model));
    }

    @Transactional
    public BankDtoModel update(BankDtoModel model) {
        bankRepositoryCRUD.update(converter.convertToBankModel(model));

        return model;
    }
}