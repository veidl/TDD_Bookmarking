package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BookmarkTest {

    @Test
    public void shouldCreateBookMark() {
        Bookmark bookmark = new Bookmark();
        Assertions.assertNotNull(bookmark);
    }

    @ParameterizedTest
    @CsvSource(value = {"https://google.at,Tag1", "https://facebook.at,31241234", "https://google.at,§$!'\"'&$§$&§"})
    public void shouldAddCustomUrlToBookmark(String url, String tag) {
        Bookmark bookmark = new Bookmark();
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl(url);
        customUrl.setTag(tag);

        bookmark.setCustomUrl(customUrl);

        Assertions.assertEquals(url, bookmark.getCustomUrl().getUrl());
        Assertions.assertEquals(tag, bookmark.getCustomUrl().getTag());
    }

    @Test
    public void shouldIncreaseRating() {
        Bookmark bookmark = new Bookmark();

        bookmark.increaseRating();

        Assertions.assertEquals(1, bookmark.getRating());
    }


}
