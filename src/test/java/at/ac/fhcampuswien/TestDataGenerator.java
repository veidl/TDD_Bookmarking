package at.ac.fhcampuswien;

import at.ac.fhcampuswien.model.Bookmark;
import at.ac.fhcampuswien.model.CustomUrl;

import java.time.LocalDateTime;

public class TestDataGenerator {

    public static LocalDateTime STATIC_DATE = LocalDateTime.of(1997, 2, 1, 10, 10);

    public static Bookmark getValidBookmarkWithTag() {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl("https://google.at");
        customUrl.setTag("myTag");

        Bookmark bookmark = new Bookmark();
        bookmark.setCustomUrl(customUrl);

        return bookmark;
    }

    public static Bookmark getCustomBookmark(String url, String tag) {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl(url);
        customUrl.setTag(tag);

        Bookmark bookmark = new Bookmark(() -> STATIC_DATE);
        bookmark.addTime();
        bookmark.setCustomUrl(customUrl);
        return bookmark;
    }

    public static Bookmark getValidBookmarkWithoutTag(String url) {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl(url);
        customUrl.setTag("tag2");

        Bookmark bookmark = new Bookmark();
        bookmark.setCustomUrl(customUrl);

        return bookmark;
    }


}
