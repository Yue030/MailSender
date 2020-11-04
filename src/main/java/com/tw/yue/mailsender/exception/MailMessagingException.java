package com.tw.yue.mailsender.exception;

public class MailMessagingException extends MailException{
    public MailMessagingException(String s) {
        super(s);
    }

    public MailMessagingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailMessagingException(Throwable throwable) {
        super(throwable);
    }
}
