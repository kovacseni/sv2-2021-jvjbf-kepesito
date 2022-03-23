package messages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private List<Message> messages = new ArrayList<>();

    public void readFromFile(Path path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch(IOException ioe) {
            throw new IllegalArgumentException("File cannot be read", ioe);
        }
        putMessagesIntoList(lines);
    }

    private void putMessagesIntoList(List<String> lines) {
        for ( int i = 0; i < lines.size(); i = i+3 ) {
            String[] tmp = lines.get(i).split(" ");
            messages.add(new Message(tmp[0], LocalTime.parse(tmp[1]), lines.get(i+1)));
        }
    }

    public boolean isMessagesInOrder() {
        if ( messages.isEmpty() || messages.size() == 1 ) {
            return true;
        }
        for ( int i = 1; i < messages.size(); i++ ) {
            if ( messages.get(i).getTime().isBefore(messages.get(i-1).getTime()) ) {
                return false;
            }
        }
        return true;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
