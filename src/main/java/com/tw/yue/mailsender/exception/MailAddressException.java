package com.tw.yue.mailsender.exception;

public class MailAddressException extends MailException {
    public MailAddressException(String s) {
        super(s);
    }

    public MailAddressException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailAddressException(Throwable throwable) {
        super(throwable);
    }
}
