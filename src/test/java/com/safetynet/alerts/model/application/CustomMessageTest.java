package com.safetynet.alerts.model.application;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomMessageTest {
    private static LocalDateTime timestamp = LocalDateTime.now();
    private static int expectStatus = 200;
    private static String expectMessage = "Status Ok";

    private CustomMessage customMessage;

    @Test
    public void testCustomMessage() {
        customMessage = new CustomMessage();
        customMessage.setTimestamp(timestamp);
        customMessage.setStatus(200);
        customMessage.setMessage("Status Ok");

        assertEquals(timestamp, customMessage.getTimestamp());
        assertEquals(expectStatus, customMessage.getStatus());
        assertEquals(expectMessage, customMessage.getMessage());
    }

    @Test
    public void testCustomMessage_with_constructor() {
        customMessage = new CustomMessage(timestamp, 200, "Status Ok");

        assertEquals(timestamp, customMessage.getTimestamp());
        assertEquals(expectStatus, customMessage.getStatus());
        assertEquals(expectMessage, customMessage.getMessage());
    }

}
