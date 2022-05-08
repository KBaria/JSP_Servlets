package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtils {
	public static void sendEmail(String host, String port,
            String senderEmail, String senderName, String password,
            String recipientEmail, String subject, String message) throws AddressException, MessagingException, UnsupportedEncodingException {
		
		// set SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		
		// create authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password);
			}
		};
		
		// create session
		Session mailSession = Session.getInstance(properties, auth);
		
		// create message
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(senderEmail, senderName));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		
		// send message
		Transport.send(msg);
	}
}
