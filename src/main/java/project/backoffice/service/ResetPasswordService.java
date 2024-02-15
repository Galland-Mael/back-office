package project.backoffice.service;

import com.mailjet.client.transactional.SendContact;
import org.springframework.stereotype.Component;
import project.backoffice.dto.MailDTO;
import project.backoffice.dto.ResetPasswordRequestDTO;
import project.backoffice.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ResetPasswordService {

    private static Map<String, ResetPasswordRequestDTO> resetPasswordRequests = new HashMap<>();
    private MailService mailService;

    public void resetPassword(String email) {
        UserDTO user = new UserDTO();
        user.setEmail(email);
        String code = generateCode();
        ResetPasswordRequestDTO resetPasswordRequest = new ResetPasswordRequestDTO();
        resetPasswordRequest.setUser(user);
        resetPasswordRequest.setCode(code);
        resetPasswordRequest.setExpirationDate(LocalDateTime.now().plusMinutes(30));
        resetPasswordRequests.put(email, resetPasswordRequest);
        MailDTO mail = createMail(user, code);
        mailService.sendEmail(mail);

    }

    public String generateCode() {
        return String.format("%04d",  new Random().nextInt(10000));
    }

    public void clearExpiredRequests() {
        for(Map.Entry<String, ResetPasswordRequestDTO> entry : resetPasswordRequests.entrySet()) {
            if(entry.getValue().getExpirationDate().isBefore(LocalDateTime.now())) {
                resetPasswordRequests.remove(entry.getKey());
            }
        }
    }

    public boolean isCodeValid(String email, String code) {
        ResetPasswordRequestDTO resetPasswordRequest = resetPasswordRequests.get(email);
        return resetPasswordRequest != null && resetPasswordRequest.getCode().equals(code);
    }

    public MailDTO createMail(UserDTO user, String code) {
        MailDTO mail = new MailDTO();
        mail.getMessageBuilder().subject("Reset your password");
        mail.getMessageBuilder()
                .htmlPart("<h1>Reset your password</h1>" +
                        "<p>Hi " + user.getFirstName() + ",</p>"
                        + "You requested to reset your password."
                        + " Here is your code: <strong>" + code + "</strong>"
                        + "<p>If you didn't request this, you can ignore this email.</p>"
                        + "<p>Thanks,</p>"
                );
        mail.setRecipients(List.of(new SendContact(user.getEmail(), user.getFirstName() + " " + user.getLastName())));
        return mail;
    }
}
