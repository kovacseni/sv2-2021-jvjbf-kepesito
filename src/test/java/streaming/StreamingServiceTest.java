package streaming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StreamingServiceTest {
    StreamingService service = new StreamingService();

    @BeforeEach
    void init() {
        Content movie = new Movie(new Video("Movie 1", 123), Genre.COMEDY, 4.5);
        Content movie2 = new Movie(new Video("Movie 2", 117), Genre.HORROR, 3.5);
        Content movie3 = new Movie(new Video("Movie 3", 122), Genre.THRILLER, 4.2);

        List<Video> parts = List.of(new Video("Part1", 45), new Video("Part2", 47), new Video("Part3", 43));
        List<Video> parts2 = List.of(new Video("Part1", 45), new Video("Part2", 47), new Video("Part3", 43), new Video("Part 4", 52));
        List<Video> parts3 = List.of(new Video("Part1", 45), new Video("Part2", 47));

        Content series = new Series("Series 1", parts, Genre.ADVENTURE, 4.1);
        Content series2 = new Series("Series 2", parts2, Genre.HORROR, 4.2);
        Content series3 = new Series("Series 3", parts3, Genre.ACTION, 4.0);

        service.addContent(movie);
        service.addContent(movie2);
        service.addContent(movie3);
        service.addContent(series);
        service.addContent(series2);
        service.addContent(series3);
    }

    @Test
    void addContentTest() {
        service.addContent(new Movie(new Video("Movie 4", 123), Genre.COMEDY, 4.5));

        assertEquals(List.of("Movie 1", "Movie 2", "Movie 3", "Series 1", "Series 2", "Series 3", "Movie 4"),
                service.getContents().stream().map(Content::getTitle).toList());
    }


    @Test
    void getContentsSortedByRatingTest() {
        assertEquals(List.of("Movie 2", "Series 3", "Series 1", "Movie 3", "Series 2", "Movie 1"),
                service.getContentsSortedByRating().stream().map(Content::getTitle).toList());
    }

    @Test
    void findByTitleTest() {
        assertEquals("Series 3", service.findByTitle("Series 3").get(0).getTitle());

        service.addContent(new Movie(new Video("Movie 1", 127), Genre.COMEDY, 4.5));

        assertEquals(List.of(123, 127), service.findByTitle("Movie 1").stream().map(Content::getLength).toList());
    }

    @Test
    void countContentByGenreTest() {
        Map<Genre, Integer> result = service.countContentByGenre();

        assertEquals(5, result.size());
        assertEquals(2, result.get(Genre.HORROR));
        assertEquals(1, result.get(Genre.COMEDY));

        service.addContent(new Movie(new Video("Movie 1", 127), Genre.COMEDY, 4.5));

        result = service.countContentByGenre();

        assertEquals(2, result.get(Genre.COMEDY));
    }
}