package com.example.models;

import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionsRequest {
    private List<IncomingChat> messages;
}
