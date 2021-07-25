package mz.ac.covid.app.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.ac.covid.app.boot.service.Service;
import mz.ac.covid.app.boot.service.SmsRequest;

@RestController
@RequestMapping("api/v1/sms")
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Validated @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }
}
