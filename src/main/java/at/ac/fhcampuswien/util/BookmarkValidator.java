package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.model.Bookmark;

import java.util.List;

public class BookmarkValidator {
    public static boolean isValid(List<Bookmark> bookmarks, Bookmark newBookmark) {
        return bookmarks.stream().map(bookmark -> bookmark.getCustomUrl().getUrl())
                .anyMatch(s -> newBookmark.getCustomUrl().getUrl().equals(s));
    }
}
