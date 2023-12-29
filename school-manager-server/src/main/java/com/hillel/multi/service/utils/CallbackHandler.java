package com.hillel.multi.service.utils;

import com.hillel.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CallbackHandler {

    public void sendCallback(Message message, URI callbackUrl) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(callbackUrl)
                .POST(HttpRequest.BodyPublishers.ofString(message.getText()))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
