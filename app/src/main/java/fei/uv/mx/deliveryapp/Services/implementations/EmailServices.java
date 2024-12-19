package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailServices {

    private final JavaMailSender mailSender;
    public EmailServices(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(User user, String token, String appUrl) {
        String recipientAddress = user.getEmail();
        String subject = "Confirmación de Registro";
        String confirmationUrl = appUrl + "/confirm?token=" + token;

        String message = "<div style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
                + "<h2 style='text-align: center; color: #4CAF50;'>¡Hola, " + user.getName() + "!</h2>"
                + "<p style='text-align: justify;'>Gracias por registrarte en <strong>DeliveryApp</strong>. Estamos emocionados de tenerte a bordo.</p>"
                + "<p style='text-align: justify;'>Para completar tu registro y comenzar a disfrutar de los beneficios de nuestra aplicación, por favor confirma tu correo electrónico haciendo clic en el botón a continuación:</p>"
                + "<div style='text-align: center; margin: 20px;'>"
                + "<a href='" + confirmationUrl + "' style='background-color: #4CAF50; color: white; padding: 12px 25px; text-decoration: none; font-weight: bold; border-radius: 5px;'>Sí, soy yo</a>"
                + "</div>"
                + "<p style='text-align: justify;'>Si no realizaste esta solicitud, por favor ignora este correo. Tu cuenta no será activada sin la confirmación.</p>"
                + "<hr style='border: none; border-top: 1px solid #ccc; margin: 20px 0;'>"
                + "<footer style='text-align: center; color: #666;'>"
                + "<p>DeliveryApp © " + LocalDate.now().getYear() + "</p>"
                + "<p>Gracias por confiar en nosotros para facilitar tus pedidos.</p>"
                + "</footer>"
                + "</div>";

        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(email);

        try {
            helper.setTo(recipientAddress);
            helper.setSubject(subject);
            helper.setText(message, true);
            mailSender.send(email);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo electrónico.");
        }
    }

}

