package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookmarkTest {

    @Test
    public void shouldCreateBookMark() {
        Bookmark bookmark = new Bookmark();
        Assertions.assertNotNull(bookmark);
    }

    @Test
    public void shouldAddUrlToBookmark() {
        Bookmark bookmark = new Bookmark();
        String url = "https://google.at";

        bookmark.setUrl(url);

        Assertions.assertEquals(url, bookmark.getUrl());
    }
}
