package com.tw.yue.mailsender.object;

import java.util.Collections;
import java.util.List;

public class Mail {
    private final String subject;
    private final String receiver;
    private final List<String> messages;

    public Mail(String subject, String receiver, List<String> messages) {
        this.subject = subject;
        this.receiver = receiver;
        this.messages = messages;
    }

    public Mail(String subject, String receiver, String message) {
        this(subject, receiver, Collections.singletonList(message));
    }

    public String getSubject() {
        return subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public List<String> getMessages() {
        return messages;
    }
}
