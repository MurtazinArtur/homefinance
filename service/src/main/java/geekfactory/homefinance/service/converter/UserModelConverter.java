package geekfactory.homefinance.service.converter;

import geekfactory.homefinance.dao.model.AccountModel;
import geekfactory.homefinance.dao.model.UserModel;
import geekfactory.homefinance.dao.model.UserRoles;
import geekfactory.homefinance.service.dto.UserDtoModel;
import geekfactory.homefinance.service.serviceImpl.AccountService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component
@Transactional
public class UserModelConverter {
    private final AccountService accountService;
    private final AccountModelConverter accountModelConverter;
    public String conditionConvert;

    public UserModelConverter(@Lazy AccountService accountService, @Lazy AccountModelConverter accountModelConverter) {
        this.accountService = accountService;
        this.accountModelConverter = accountModelConverter;
    }

    public UserDtoModel convertToUserDtoModel(UserModel userModel) {
        UserDtoModel userDtoModel = new UserDtoModel();

        if (userModel != null) {
            if(userModel.getId() != null){
                userDtoModel.setId(Math.toIntExact(userModel.getId()));
            }else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (userModel.getUser() != null) {
                userDtoModel.setUser(userModel.getUser());
            } else {
                conditionConvert = "Поле user не может быть пустым";
            }
            if (userModel.getPassword() != null) {
                userDtoModel.setPassword(userModel.getPassword());
            } else {
                conditionConvert = "Поле password не может быть пустым";
            }
            if (userModel.getUserRole() != null) {
                userDtoModel.setUserRole(String.valueOf(userModel.getUserRole()));
            } else {
                conditionConvert = "Поле userRole не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return userDtoModel;
    }

    public Collection<UserDtoModel> convertCollectionUserDtoModels(Collection<UserModel> userModelCollection) {
        Collection<UserDtoModel> userDtoModelCollection = new ArrayList<>();

        for (UserModel userModel : userModelCollection) {
            userDtoModelCollection.add(convertToUserDtoModel(userModel));
        }
        return userDtoModelCollection;
    }

    public UserModel convertToUserModel(UserDtoModel userDtoModel) {
        UserModel userModel = new UserModel();

        if (userDtoModel != null) {
            if (userDtoModel.getId() != 0){
                userModel.setId(Long.valueOf(userDtoModel.getId()));
            }else {
                conditionConvert = "Поле id не может быть пустым";
            }
            if (userDtoModel.getUser() != null) {
                userModel.setUser(userDtoModel.getUser());
            } else {
                conditionConvert = "Поле user не может быть пустым";
            }
            if (userDtoModel.getPassword() != null) {
                userModel.setPassword(userDtoModel.getPassword());
            } else {
                conditionConvert = "Поле password не может быть пустым";
            }
            if (userDtoModel.getUserRole() != null) {
                userModel.setUserRole(UserRoles.valueOf(userDtoModel.getUserRole()));
            } else {
                conditionConvert = "Поле userRole не может быть пустым";
            }
        } else {
            conditionConvert = "Ошибка конвертации модели";
        }
        return userModel;
    }

    public Collection<UserModel> convertCollectionUserModels(Collection<UserDtoModel> userDtoModelCollection) {
        Collection<UserModel> userModelCollection = new ArrayList<>();

        for (UserDtoModel userDtoModel : userDtoModelCollection) {
            userModelCollection.add(convertToUserModel(userDtoModel));
        }
        return userModelCollection;
    }
}