package com.levserj.toll.util;

import com.levserj.toll.domain.Trip;
import com.levserj.toll.domain.User;
import com.levserj.toll.repository.UserRepo;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import java.util.Properties;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 *
 * Sends email to the user, using SimpleJavaEmail library.
 * In this example Gmail account is used, only "username" and
 * "password" in Mailer constructor need to be replaced with proper values.
 */
@Component
public class EmailSender {

    @Autowired
    UserRepo userRepo;

    public void sendInvoice(User user, Trip trip) {
        String userEmail = user.getEmail();
        String fName = user.getFirstName();
        String lName = user.getLastName();
        String dateAndTime = trip.getDateAndTime();
        Float payment = trip.getPayment();
        Email email = new Email();
        // Needed to work with G, didn't find another way to set this property
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true);
        email.setFromAddress("Paid Roads Company", "prc@gmail.com");
        email.addRecipient(String.format("%s %s", fName, lName), userEmail, Message.RecipientType.TO);
        email.setText(String.format("Dear %s %s", fName, lName) + "\n"
                + String.format("You payment for the trip on Paid Road Company on %s is %f.2 \nThank you",
                dateAndTime, payment));
        email.setSubject("Toll invoice");
        Mailer mailer = new Mailer(new ServerConfig("smtp.gmail.com", 587, "username", "password"));
        mailer.applyProperties(props);
        mailer.trustAllSSLHosts(true);
        mailer.sendMail(email);
    }
}
