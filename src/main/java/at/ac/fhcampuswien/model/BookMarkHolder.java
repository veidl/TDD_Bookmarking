package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.util.BookmarkValidator;
import at.ac.fhcampuswien.util.UrlValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookMarkHolder {
    private final List<Bookmark> bookMarks;
    private final Map<Bookmark, Bookmark> associatedBookmarks;

    public BookMarkHolder() {
        this.associatedBookmarks = new HashMap<>();
        this.bookMarks = new ArrayList<>();
    }

    public int getBookMarkCount() {
        return this.bookMarks.size();
    }

    public List<Bookmark> getBookmarks() {
        return bookMarks;
    }

    public void addBookmark(Bookmark bookMark) {
        int index = BookmarkValidator.isValid(this.bookMarks, bookMark);

        if (index >= 0) {
            this.bookMarks.get(index).increaseRating();
        } else {
            this.bookMarks.forEach(bookmark -> {
                if (UrlValidator.isAssociatedWithAnyUrl(bookmark.getCustomUrl().getUrl(), bookMark.getCustomUrl().getUrl())) {
                    this.associatedBookmarks.put(bookmark, bookMark);
                }
            });
            this.bookMarks.add(bookMark);
        }
    }

    public int getSecureUrlCount() {
        return (int) this.bookMarks.stream()
                .map(bookmark -> bookmark.getCustomUrl().getUrl())
                .filter(UrlValidator::isSecureUrl)
                .count();
    }

    public List<Bookmark> getBookmarksByTag(List<String> myTag) {
        return this.bookMarks.stream()
                .filter(bookmark -> myTag.contains(bookmark.getCustomUrl().getTag()))
                .collect(Collectors.toList());
    }

    public Map<Bookmark, Bookmark> getAssociatedBookMarksMap() {
        return this.associatedBookmarks;

    }

    public void removeTag(int i) {
        if(i >= this.bookMarks.size()) {
            throw new UnsupportedOperationException("Out of index");
        }

        this.bookMarks.get(i).getCustomUrl().setTag(null);
    }

    public void removeURL(int i) {
        if(i >= this.bookMarks.size()) {
            throw new UnsupportedOperationException("Out of index");
        }
        this.bookMarks.get(i).getCustomUrl().removeURL();
    }
}
