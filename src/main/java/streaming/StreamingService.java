package streaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StreamingService {

    private List<Content> contents = new ArrayList<>();

    public void addContent(Content content) {
        contents.add(content);
    }

    public List<Content> getContentsSortedByRating() {
        return null;
    }

    public List<Content> findByTitle(String title) {
        List<Content> result = new ArrayList<>();
        for (Content actual : contents ) {
            if ( actual.getTitle().equals(title)) {
                result.add(actual);
            }
        }
        return result;
    }

    public Map<Genre,Integer> countContentByGenre() {
        return null;
    }

    public List<Content> getContents() {
        return contents;
    }
}
