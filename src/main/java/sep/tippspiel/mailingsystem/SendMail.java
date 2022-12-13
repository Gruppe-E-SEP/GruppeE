package sep.tippspiel.mailingsystem;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import sep.tippspiel.user.Users;

public class SendMail {
    @Autowired Users users;

    public static void main(String[] args){
        String to = "kevinprinz.1@web.de";

        String from = "tippspiel@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("tippspiel", "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");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Authentication Code!");
            message.setText("Code");
            System.out.println("sending ...");
            Transport.send(message);
            System.out.println("Sent message sucessfully!");
        }
        catch (MessagingException mex){
            mex.printStackTrace();
        }





    }
}
