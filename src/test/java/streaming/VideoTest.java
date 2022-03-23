package streaming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {
    @Test
    void createVideoTest() {
        Video video = new Video("Best Video", 121);

        assertEquals("Best Video", video.getTitle());
        assertEquals(121, video.getLength());
    }
}