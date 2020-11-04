package com.tw.yue.mailsender.exception;

public class MailEncodingException extends MailException{
    public MailEncodingException(String s) {
        super(s);
    }

    public MailEncodingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MailEncodingException(Throwable throwable) {
        super(throwable);
    }
}