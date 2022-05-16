package at.ac.fhcampuswien.model;

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

    @Test
    public void shouldThrowIllegalArgumentExceptionForInvalidUrl() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Bookmark().setUrl("notAValidUrl"));
        Assertions.assertEquals("Not a valid URL", exception.getMessage());
    }
}
