package kts.project.service;

/**
 * This class represents Email Service
 *
 */
import kts.project.model.User;
import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    /**
     * This method is sending Email to new User with link for verification
     * @param user
     */
        public void sendMail(User user) {
            Thread t = new Thread(() -> {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                final String username = "realestate.n.a.wro@gmail.com";
                final String password = "fabuloso";
                final String subject = "Verifikacija lozinke";

                final String messageText = String.format("Uspešno ste se ulogovali na http://localhost:8080/#/authenticate/%d. \nMolimo Vas da klikom na link iznad potvrdite svoju lozinku.", user.getId());

                Session session = Session.getDefaultInstance(properties, new Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                /**    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail())); **/
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("iceipice.trio@gmail.com"));
                    message.setSubject(subject);
                    message.setText(messageText);
                    Transport.send(message);

                    System.out.println("Message sent!");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }




}
