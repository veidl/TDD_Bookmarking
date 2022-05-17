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

    public static Bookmark getValidBookmarkWithoutTag(String url) {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl(url);
        customUrl.setTag("tag2");

        Bookmark bookmark = new Bookmark();
        bookmark.setCustomUrl(customUrl);

        return bookmark;
    }


}
