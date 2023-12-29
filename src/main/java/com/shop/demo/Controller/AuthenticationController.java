package com.shop.demo.Controller;

import com.shop.demo.Model.Authentication.AuthenticationRequest;
import com.shop.demo.Model.Authentication.AuthenticationResponse;
import com.shop.demo.Model.Authentication.RegisterRequest;
//import com.shop.demo.Response.ResponseHandler;
import com.shop.demo.Response.ResponseHandler;
import com.shop.demo.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<Object> register(  @Valid  @RequestBody RegisterRequest request){

            AuthenticationResponse response = service.register(request);
            String message = "Successfully created User " + request.getEmail();
            return ResponseHandler.generateResponse(message, HttpStatus.CREATED, response);


    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

}
