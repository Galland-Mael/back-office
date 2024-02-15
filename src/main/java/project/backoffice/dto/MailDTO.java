package project.backoffice.dto;

import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.TransactionalEmail;
import lombok.Data;

import java.util.List;

@Data
public class MailDTO {
    private List<SendContact> recipients;
    private TransactionalEmail.TransactionalEmailBuilder messageBuilder;
}
