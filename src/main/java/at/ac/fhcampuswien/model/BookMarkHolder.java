package at.ac.fhcampuswien.model;

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

    public void addBookmark(Bookmark bookMarks) {
        this.bookMarks.add(bookMarks);
    }
}
