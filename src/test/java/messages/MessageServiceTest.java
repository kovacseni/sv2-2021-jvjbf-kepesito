package messages;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {
    @Test
    void testReadFromFile() {
        MessageService messageService = new MessageService();

        assertTrue(messageService.getMessages().isEmpty());

        messageService.readFromFile(Path.of("src/test/resources/data.txt"));

        assertEquals(6, messageService.getMessages().size());
        assertEquals(List.of("John", "Jane", "John", "John", "Jane", "Jane"), messageService.getMessages().stream().map(Message::getSender).toList());
    }

    @Test
    void isMessagesInOrderTest() {
        MessageService messageService = new MessageService();
        messageService.readFromFile(Path.of("src/test/resources/data.txt"));

        assertTrue(messageService.isMessagesInOrder());

        messageService = new MessageService();
        messageService.readFromFile(Path.of("src/test/resources/data2.txt"));

        assertFalse(messageService.isMessagesInOrder());
    }
}