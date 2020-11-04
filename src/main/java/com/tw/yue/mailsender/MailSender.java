package com.tw.yue.mailsender;

import com.tw.yue.mailsender.exception.*;
import com.tw.yue.mailsender.object.Mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MailSender {
    private static final Map<String, MailSender> flyweight = new HashMap<>();

    protected static final String mailServer = "smtp.gmail.com";
    protected static final int mailPort = 465;

    protected final String senderName;
    protected final String senderMail;
    protected final String senderPassword;

    public static MailSender getInstance(final String senderName,
                  final String senderMail, final String senderPassword) {
        if (!(flyweight.containsKey(senderMail))) {
            flyweight.put(senderMail, new MailSender(senderName, senderMail, senderPassword));
        }
        return flyweight.get(senderMail);
    }

    protected MailSender(String senderName, String senderMail,
                         String senderPassword) {
        this.senderName = senderName;
        this.senderMail = senderMail;
        this.senderPassword = senderPassword;
    }

    protected boolean send(Mail mail) throws MailException {
        try {
            Properties prop = initializeProperties();
            Session mailSession = initializeSession(prop);
            Message message = initializeMessage(mail, mailSession);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new MailTransportException(e);
        }
    }

    protected Properties initializeProperties() {
        Properties prop = new Properties();
        prop.put("mail.host", mailServer);
        prop.put("mail.smtp.port", mailPort);
        prop.put("mail.smtp.socketFactory.port", mailPort);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", false);
        prop.put("mail.smtp.auth", true);
        prop.put("mail.transport.protocol", "smtp");

        return prop;
    }

    protected Session initializeSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
        });
    }

    protected Message initializeMessage(Mail mail, Session mailSession) throws MailException {
        Message message = new MimeMessage(mailSession);
        try {
            message.setSubject(mail.getSubject());
            message.setSentDate(new Date());
            message.setText(listToString(mail.getMessages()));
            message.setFrom(new InternetAddress(senderMail, senderName));
            message.setRecipients(Message.RecipientType.TO, getInternetAddress(mail.getReceiver()));
        } catch (MessagingException e) {
            throw new MailMessagingException(e);
        } catch (UnsupportedEncodingException e) {
            throw new MailEncodingException(e);
        }
        return message;
    }

    protected String listToString(List<String> messages) {
        StringBuilder builder = new StringBuilder();

        for (String s : messages) {
            builder.append(s).append("\n");
        }
        builder.append("\n\n\u6ce8\u610f\uff1a\u6b64\u6d88\u606f\u662f\u81ea\u52d5\u767c\u9001\u7684\uff0c\u8acb\u4e0d\u8981\u76f4\u63a5\u56de\u590d\uff01");

        return builder.toString();
    }

    protected InternetAddress[] getInternetAddress(String receiver) throws MailAddressException {
        try {
            return InternetAddress.parse(receiver);
        } catch (AddressException e) {
            throw new MailAddressException(e);
        }
    }
}
