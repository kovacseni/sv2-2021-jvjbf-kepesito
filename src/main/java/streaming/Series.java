package streaming;

import java.util.List;

public class Series implements Content{
    private String title;
    private List<Video> videos;
    private Genre genre;
    private double rating;

    public Series(String title, List<Video> videos, Genre genre, double rating) {
        this.title = title;
        if ( videos == null || videos.isEmpty() ) {
            throw new IllegalArgumentException("Cannot create Series with empty or null list!");
        }
        this.videos = videos;
        this.genre = genre;
        this.rating = rating;

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Video actual : videos) {
            totalLength += actual.getLength();
        }
        return totalLength;
    }

    @Override
    public Genre getGenre() {
        return genre;
    }
}
