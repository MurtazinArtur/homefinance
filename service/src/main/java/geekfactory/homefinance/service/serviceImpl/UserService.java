package geekfactory.homefinance.service.serviceImpl;

import geekfactory.homefinance.dao.repository.UserRepository;
import geekfactory.homefinance.service.converter.AccountModelConverter;
import geekfactory.homefinance.service.converter.UserModelConverter;
import geekfactory.homefinance.service.dto.AccountDtoModel;
import geekfactory.homefinance.service.dto.UserDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@Service("userService")
public class UserService {
    private UserModelConverter userModelConverter;
    private AccountModelConverter accountModelConverter;
    private UserRepository userRepository;
    private AccountService accountService;

    @Autowired
    public UserService(UserModelConverter userModelConverter, AccountModelConverter accountModelConverter,
                       UserRepository userRepository, AccountService accountService) {
        this.userModelConverter = userModelConverter;
        this.accountModelConverter = accountModelConverter;
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public Optional<UserDtoModel> findById(Long id) {
        return Optional.ofNullable(userModelConverter.convertToUserDtoModel(userRepository.findById(id).get()));
    }

    public Optional<UserDtoModel> findByName(String name) {
        Optional<UserDtoModel> resultFindUser =
                Optional.ofNullable(userModelConverter.convertToUserDtoModel(userRepository.findByName(name).get()));
        if (!resultFindUser.isPresent()) {
            throw new UsernameNotFoundException("User " + name + " name not found!");
        }

        return resultFindUser;
    }

    public Collection<UserDtoModel> findAll() {
        return userModelConverter.convertCollectionUserDtoModels(userRepository.findAll());
    }

    public void save(UserDtoModel userDtoModel) {
        userRepository.save(userModelConverter.convertToUserModel(userDtoModel));
    }

    public UserDtoModel update(UserDtoModel userDtoModel) {
        userRepository.update(userModelConverter.convertToUserModel(userDtoModel));

        return userDtoModel;
    }

    public void remove(UserDtoModel userDtoModel) {
        Collection<AccountDtoModel> accountDtoModelCollection = accountService.findAll();

        for (AccountDtoModel accountDtoModel : accountDtoModelCollection) {
            if (accountModelConverter.convertToAccountModel(accountDtoModel).equals(accountDtoModel.getUserModel())) {
                accountDtoModel.setUserModel(null);
                accountService.update(accountDtoModel);
            }
        }

        userRepository.remove(userModelConverter.convertToUserModel(userDtoModel));
    }
}