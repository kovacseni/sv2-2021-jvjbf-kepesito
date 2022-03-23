package streaming;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {
    @Test
    void createSeriesTest() {
        List<Video> videos = List.of(new Video("Part1", 45), new Video("Part2", 47), new Video("Part3", 43));
        Series series = new Series("Best Series", videos, Genre.ADVENTURE, 4.6);

        assertEquals("Best Series", series.getTitle());
        assertEquals(Genre.ADVENTURE, series.getGenre());

        assertEquals(4.6, series.getRating());
    }

    @Test
    void createServiceWithNullListTest() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> new Series("Best Series", null, Genre.ADVENTURE, 4.6));
        assertEquals("Cannot create Series with empty or null list!", iae.getMessage());
    }

    @Test
    void createServiceWithEmptyListTest() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> new Series("Best Series", List.of(), Genre.ADVENTURE, 4.6));
        assertEquals("Cannot create Series with empty or null list!", iae.getMessage());
    }

    @Test
    void getLengthTest() {
        List<Video> videos = List.of(new Video("Part1", 45), new Video("Part2", 47), new Video("Part3", 43));
        Series series = new Series("Best Series", videos, Genre.ADVENTURE, 4.6);
        List<Video> videos2 = List.of(new Video("Part1", 45), new Video("Part2", 47));
        Series series2 = new Series("Good Series", videos2, Genre.ACTION, 4.5);

        assertEquals(135, series.getLength());
        assertEquals(92, series2.getLength());
    }

    @Test
    void compareToTest() {
        List<Video> videos = List.of(new Video("Part1", 45), new Video("Part2", 47), new Video("Part3", 43));
        Series series = new Series("Best Series", videos, Genre.ADVENTURE, 4.6);
        List<Video> videos2 = List.of(new Video("Part1", 45), new Video("Part2", 47));
        Series series2 = new Series("Good Series", videos2, Genre.ACTION, 4.7);

        assertTrue(series.compareTo(series2) < 0);
    }

}