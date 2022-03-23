package messages;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test
    void createMessageTest() {
        Message message = new Message("John", LocalTime.of(12, 30), "Hello");

        assertEquals("John", message.getSender());
        assertEquals(LocalTime.of(12, 30), message.getTime());
        assertEquals("Hello", message.getMessageText());
    }
}