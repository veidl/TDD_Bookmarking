package at.ac.fhcampuswien;

import at.ac.fhcampuswien.model.Bookmark;
import at.ac.fhcampuswien.model.CustomUrl;

public class TestDataGenerator {

    public static Bookmark getValidBookmarkWithTag() {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl("https://google.at");
        customUrl.setTag("myTag");

        Bookmark bookmark = new Bookmark();
        bookmark.setCustomUrl(customUrl);

        return bookmark;
    }

    public static Bookmark getValidBookmarkWithoutTag() {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl("https://google.at");

        Bookmark bookmark = new Bookmark();
        bookmark.setCustomUrl(customUrl);

        return bookmark;
    }
}
