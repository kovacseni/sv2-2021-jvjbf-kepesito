package streaming;

public interface Content extends  Comparable<Content>{
    String getTitle();
    double getRating();
    int getLength();
    Genre getGenre();

    @Override
    default int compareTo(Content o) {
        return (int) (this.getRating() - o.getRating());
    }
}
