package project.backoffice.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.backoffice.dto.MailDTO;

@Component
public class MailService {

    @Value("${mailjet.api-key}")
    private String API_KEY;

    @Value("${mailjet.secret-key}")
    private String API_SECRET;

    @Value("${mailjet.sender-email}")
    private String SENDER_EMAIL;

    @Value("${mailjet.sender-name}")
    private String SENDER_NAME;

    public void sendEmail(ClientOptions options, MailDTO mailData) {
        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message = mailData.getMessageBuilder()
                .to(mailData.getRecipients())
                .from(new SendContact(SENDER_EMAIL, SENDER_NAME))
                .trackOpens(TrackOpens.ENABLED)
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(MailDTO mailData) {
        ClientOptions options = ClientOptions.builder()
                .apiKey(API_KEY)
                .apiSecretKey(API_SECRET)
                .build();
        sendEmail(options, mailData);
    }
}