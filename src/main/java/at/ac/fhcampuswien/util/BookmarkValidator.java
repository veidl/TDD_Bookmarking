package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.model.Bookmark;

import java.util.List;
import java.util.stream.IntStream;

public class BookmarkValidator {

    private BookmarkValidator() {
        // intentionally empty
    }

    public static int isValid(List<Bookmark> bookmarks, Bookmark newBookmark) {
        return IntStream.range(0, bookmarks.size())
                .filter(i -> bookmarks.get(i).getCustomUrl().getUrl().equals(newBookmark.getCustomUrl().getUrl()))
                .findFirst()
                .orElse(-1);

    }
}
