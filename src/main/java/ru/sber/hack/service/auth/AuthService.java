package ru.sber.hack.service.auth;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sber.hack.domain.entity.UserEntity;
import ru.sber.hack.domain.model.request.AuthRequest;
import ru.sber.hack.domain.model.response.AuthResponse;
import ru.sber.hack.repository.UserEntityRepository;

import java.util.Objects;

@Service
public class AuthService {

    private final static Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserEntityRepository userEntityRepository;

    public AuthService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public AuthResponse signIn(AuthRequest request) {

        if (validateRequestParams(request)) {
            UserEntity user = userEntityRepository.findByUserNameAndPassword(request.getUserName(), request.getPassword());

            if (Objects.nonNull(user)) {
                String token = generateToken();
                return new AuthResponse(request.getUserName(), token);
            }
        }

        return new AuthResponse("Invalid auth credentials");
    }

    private boolean validateRequestParams(AuthRequest request) {
        if (StringUtils.isNotBlank(request.getUserName()) &&
            StringUtils.isNotBlank(request.getPassword())) {
            return true;
        }
        return false;
    }

    private String generateToken() {
        return RandomStringUtils.random(20, true, true);
    }
}
