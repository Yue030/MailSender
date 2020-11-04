package com.tw.yue.mailsender.exception;

public class MailTransportException extends MailException {
    public MailTransportException(String s) {
        super(s);
    }

    public MailTransportException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailTransportException(Throwable throwable) {
        super(throwable);
    }
}
