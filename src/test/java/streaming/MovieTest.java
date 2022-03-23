package streaming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @Test
    void createMovieTest() {
        Content movie = new Movie(new Video("Movie1", 121), Genre.ACTION, 4.5);

        assertEquals("Movie1", movie.getTitle());
        assertEquals(121, movie.getLength());
        assertEquals(Genre.ACTION, movie.getGenre());
        assertEquals(4.5, movie.getRating());
    }

    @Test
    void compareToTest() {
        Content movie = new Movie(new Video("Movie1", 121), Genre.ACTION, 4.5);
        Content movie2 = new Movie(new Video("Movie2", 122), Genre.ACTION, 4.5);
        Content movie3 = new Movie(new Video("Movie3", 123), Genre.ACTION, 4.7);

        assertEquals(0, movie.compareTo(movie2));
        assertTrue(movie.compareTo(movie3) < 0);
        assertTrue(movie3.compareTo(movie2) > 0);
    }
}