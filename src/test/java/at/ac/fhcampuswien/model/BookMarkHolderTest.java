package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookMarkHolderTest {

    @Test
    void shouldCreateBookMarkHolder() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        Assertions.assertNotNull(bookMarkHolder);
    }

    @Test
    void shouldAddBookMarkToBookMarkHolder() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();

        bookMarkHolder.addBookmark(validBookmarkWithTag);

        Assertions.assertNotNull(bookMarkHolder.getBookmarks());
        Assertions.assertEquals(1, bookMarkHolder.getBookMarkCount());

    }

    @Test
    void shouldIncreaseRatingForDuplicate() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        Assertions.assertEquals(1, bookMarkHolder.getBookMarkCount());
        Assertions.assertEquals(1, bookMarkHolder.getBookmarks().get(0).getRating());
    }

    @Test
    void shouldIncreaseCountForSecureUrl() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        Assertions.assertEquals(2, bookMarkHolder.getSecureUrlCount());
    }
}
