package com.silkroad.BitsBids.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chat {
    enum MessageType{
        CHAT, LEAVE, JOIN
    }
    private String content;
    private String sender;
    private MessageType messageType;
}
