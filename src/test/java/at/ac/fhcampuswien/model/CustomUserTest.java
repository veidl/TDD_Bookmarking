package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomUserTest {

    @Test
    void shouldCreateUser() {
        CustomUser user = new CustomUser("init");
        Assertions.assertNotNull(user);
    }

    @Test
    void shouldCreateUserWithNameAndUniqueId() {
        CustomUser customUser = new CustomUser("thomas");

        Assertions.assertNotNull(customUser.getId());
        Assertions.assertEquals("thomas", customUser.getUserName());
    }

    @Test
    void shouldSupportBookmarksForEachUser() {
        CustomUser user = new CustomUser("oliver");
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        bookMarkHolder.addBookmark(validBookmarkWithTag);

        Assertions.assertNotNull(user.getMyBookmarks());
        Assertions.assertEquals(1, user.getMyBookmarks().getBookmarks().size());
        Assertions.assertEquals(validBookmarkWithTag, user.getMyBookmarks().getBookmarks().get(0));
    }
}
