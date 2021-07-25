package mz.ac.covid.app.boot.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class MailService {
  private static final Logger logger = LoggerFactory.getLogger(MailService.class);

  public String sendTextEmail(Email to) throws IOException {
    // the sender email should be the same as we used to Create a Single Sender
    // Verification
    Email from = new Email("hello@bit.co.mz");

    String subject = "Notificação de Vacinação: SMM Covid App";

    // Email to = new Email("daltonharvey.manusse@icloud.com");

    Content content = new Content("text/plain",
        "Saudações Cordiais, informamos que devera fazer-se presente no dia 21-08-2021, das 8 - 9h para tomar a sua segunda dose da vacina contra o Covid-19. Apresente o seu documento de identificação na entrada. Obrigado!");

    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");

    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
      return response.getBody();
    } catch (IOException ex) {
      throw ex;
    }
  }
}