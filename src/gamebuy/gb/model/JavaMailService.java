/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebuy.gb.model;

import gamebuy.gb.domain.Customer;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Administrator
 */
public class JavaMailService {
    public void sendPassword(Customer c){
        String host = "smtp.gmail.com";
        int port = 587;
        final String username = "uuu.gamebuy@gmail.com";
        final String password = "gamebuy2016";//your password

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress("admin@uuu.com.tw"));//虛擬寄件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getEmail())); //收件人
            message.setSubject("Your GameBuy Information!!");
            message.setText("Dear"+c.getName()+" \n\n 你查詢密碼的結果如下: \n\n " + c.getPassword());

            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, username, password);
            Transport.send(message);
            System.out.println("寄送email結束.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
