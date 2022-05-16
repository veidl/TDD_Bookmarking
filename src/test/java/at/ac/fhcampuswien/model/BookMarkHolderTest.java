package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookMarkHolderTest {

    @Test
    public void shouldCreateBookMarkHolder() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        Assertions.assertNotNull(bookMarkHolder);
    }

    @Test
    public void shouldAddBookMarkToBookMarkHolder() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();

        bookMarkHolder.addBookmark(validBookmarkWithTag);

        Assertions.assertNotNull(bookMarkHolder.getBookmarks());
        Assertions.assertEquals(1, bookMarkHolder.getBookMarkCount());

    }
}
