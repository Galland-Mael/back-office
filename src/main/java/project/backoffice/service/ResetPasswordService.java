package project.backoffice.service;

import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.TransactionalEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backoffice.dto.MailDTO;
import project.backoffice.dto.ResetPasswordDTO;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {

    @Value("${base-url}")
    private String BASE_URL;

    private final MailService mailService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO.getToken() == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, MessageExceptionEnum.RESET_PASSWORD_TOKEN_INVALID);
        }
        User user = userRepository.findByToken(resetPasswordDTO.getToken())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, MessageExceptionEnum.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userRepository.save(user);
    }

    public void askResetPassword(ResetPasswordDTO resetPasswordDTO) throws ApiException {
        User user = userRepository.findByEmail(resetPasswordDTO.getEmail())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, MessageExceptionEnum.USER_NOT_FOUND));
        user.setToken(jwtService.generateToken(user));
        userRepository.save(user);
        mailService.sendEmail(createMail(user));
    }

    private MailDTO createMail(User user) {
        MailDTO mail = new MailDTO();


        String resetPasswordUrl = BASE_URL + "/reset-password?token=" + user.getToken();
        TransactionalEmail.TransactionalEmailBuilder mailBuilder =
                TransactionalEmail.builder()
                        .subject("Rosco > Password reset")
                        .htmlPart("<h3>Reset your password</h3>" +
                            "<p>Click on the link below to reset your password:</p>" +
                            "<p><a href='" + resetPasswordUrl + "'>Reset Password</a>" +
                            "<p>If you can't click on the link, please copy and paste the following URL in your browser: " + resetPasswordUrl + "</p>" +
                            "</p><p>Regards,<br>Rosco Team</p>");
        mail.setMessageBuilder(mailBuilder);
        mail.setRecipients(List.of(new SendContact(user.getEmail(), user.getFirstName() + " " + user.getLastName())));
        return mail;
    }
}
