package mz.ac.covid.app.boot.service;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
