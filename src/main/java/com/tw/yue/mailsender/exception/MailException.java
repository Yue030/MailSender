package com.tw.yue.mailsender.exception;

public class MailException extends Exception {
    public MailException(String s) {
        super(s);
    }

    public MailException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailException(Throwable throwable) {
        super(throwable);
    }
}

