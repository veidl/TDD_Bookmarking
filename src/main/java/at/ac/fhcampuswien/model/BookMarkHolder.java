package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.util.BookmarkValidator;
import at.ac.fhcampuswien.util.UrlValidator;

import java.util.ArrayList;
import java.util.List;

public class BookMarkHolder {
    private final List<Bookmark> bookMarks;

    public BookMarkHolder() {
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
            this.bookMarks.add(bookMark);
        }
    }

    public int getSecureUrlCount() {
       return (int) this.bookMarks.stream()
               .map(bookmark -> bookmark.getCustomUrl().getUrl())
               .map(UrlValidator::isSecureUrl)
               .count();
    }
}
