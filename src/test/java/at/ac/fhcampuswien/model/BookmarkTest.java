package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;

class BookmarkTest {

    @Test
    void shouldCreateBookMark() {
        Bookmark bookmark = new Bookmark();
        Assertions.assertNotNull(bookmark);
    }

    @ParameterizedTest
    @CsvSource(value = {"https://google.at,Tag1", "https://facebook.at,31241234", "https://google.at,§$!'\"'&$§$&§"})
    void shouldAddCustomUrlToBookmark(String url, String tag) {
        Bookmark bookmark = new Bookmark();
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl(url);
        customUrl.setTag(tag);

        bookmark.setCustomUrl(customUrl);

        Assertions.assertEquals(url, bookmark.getCustomUrl().getUrl());
        Assertions.assertEquals(tag, bookmark.getCustomUrl().getTag());
    }

    @Test
    void shouldIncreaseRating() {
        Bookmark bookmark = new Bookmark();

        bookmark.increaseRating();

        Assertions.assertEquals(1, bookmark.getRating());
    }

    // usually I'd mock the clock - not sure if we are supposed to use mockito
    @Test
    void shouldAddTimestampToBookmark() {
        LocalDateTime time = LocalDateTime.now();
        Bookmark bookmark = new Bookmark(() -> time);

        bookmark.addTime();

        Assertions.assertEquals(bookmark.getTimeStamp(), time);
    }
}
