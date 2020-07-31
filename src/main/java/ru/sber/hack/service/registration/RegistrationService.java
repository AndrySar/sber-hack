package ru.sber.hack.service.registration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sber.hack.converter.UserEntityConverter;
import ru.sber.hack.domain.entity.UserEntity;
import ru.sber.hack.domain.model.request.RegistrationRequest;
import ru.sber.hack.domain.model.response.RegistrationResponse;
import ru.sber.hack.repository.UserEntityRepository;

import java.util.Objects;

@Service
public class RegistrationService {

    private final static Logger log = LoggerFactory.getLogger(RegistrationService.class);

    private final UserEntityRepository userEntityRepository;

    public RegistrationService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public RegistrationResponse signUp(RegistrationRequest request) {
        if (validateRegistrationRequest(request)) {
            log.info("User {} success validate", request.getUserName());

            UserEntity newUser = UserEntityConverter.convert(request);
            UserEntity oldUser = userEntityRepository.findByUserName(newUser.getUserName());

            if (Objects.nonNull(oldUser)) {
                return new RegistrationResponse(oldUser.getUserName(), "User already exists");
            }

            userEntityRepository.save(newUser);

            return new RegistrationResponse(request.getUserName(), "");
        }
        return new RegistrationResponse("","Invalid authorization");
    }

    private boolean validateRegistrationRequest(RegistrationRequest request) {
        if (StringUtils.isNotBlank(request.getUserName()) &&
            StringUtils.isNotBlank(request.getFirstName()) &&
            StringUtils.isNotBlank(request.getLastName()) &&
            StringUtils.isNotBlank(request.getPassword()) &&
            StringUtils.isNotBlank(request.getEmail())) {
            return true;
        }
        return false;
    }
}
