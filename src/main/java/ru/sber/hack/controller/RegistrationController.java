package ru.sber.hack.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.hack.domain.model.request.AuthRequest;
import ru.sber.hack.domain.model.request.RegistrationRequest;
import ru.sber.hack.domain.model.response.AuthResponse;
import ru.sber.hack.domain.model.response.RegistrationResponse;
import ru.sber.hack.service.auth.AuthService;
import ru.sber.hack.service.registration.RegistrationService;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    public RegistrationController(AuthService authService, RegistrationService registrationService) {
        this.authService = authService;
        this.registrationService = registrationService;
    }

    @RequestMapping(path = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = registrationService.signUp(request);

        if (StringUtils.isNotEmpty(response.getError())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getError());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(path = "/user/signin", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody AuthRequest request) {
        AuthResponse response = authService.signIn(request);

        if (StringUtils.isNotEmpty(response.getError())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getError());
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
