package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;

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
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithoutTag("https://facebook.com"));

        Assertions.assertEquals(2, bookMarkHolder.getSecureUrlCount());
        Assertions.assertEquals(2, bookMarkHolder.getBookMarkCount());
    }

    @ParameterizedTest
    @CsvSource(value = {"https://facebook.com,myTag,2", "https://orf.at,tag2,1", "file://test.at,cool,1"})
    void shouldFilterBookMarksBasedOnTag(String url, String tag, int result) {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        bookMarkHolder.addBookmark(validBookmarkWithTag);
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark(url, tag));

        Assertions.assertEquals(2, bookMarkHolder.getBookMarkCount());
        Assertions.assertEquals(result, bookMarkHolder.getBookmarksByTag(Collections.singletonList(tag)).size());
    }

    @Test
    void shouldFilterBookMarksBasedOnMultipleTags() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        bookMarkHolder.addBookmark(validBookmarkWithTag);
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://orf.at", "mySecondTag"));

        Assertions.assertEquals(2, bookMarkHolder.getBookMarkCount());
        Assertions.assertEquals(2, bookMarkHolder.getBookmarksByTag(Arrays.asList("mySecondTag", "myTag")).size());
    }

    @Test
    void shouldFindNoBookmarkWhenTagIsNotPresent() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        bookMarkHolder.addBookmark(validBookmarkWithTag);
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://orf.at", "mySecondTag"));

        Assertions.assertEquals(2, bookMarkHolder.getBookMarkCount());
        Assertions.assertEquals(0, bookMarkHolder.getBookmarksByTag(Arrays.asList("hugo", "hugo2")).size());
    }

    @Test
    void shouldAddBookMarkToAssociateListForSameDomain() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        bookMarkHolder.addBookmark(validBookmarkWithTag);
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://google.at/mySubDirectory", "mySecondTag"));
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://orf.at/vogel", "mySecondTag"));
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://orf.at/testinger", "mySecondTag"));
        bookMarkHolder.addBookmark(TestDataGenerator.getCustomBookmark("https://news.vogle.at/testinger", "mySecondTag"));

        Assertions.assertEquals(5, bookMarkHolder.getBookMarkCount());
        Assertions.assertEquals(2, bookMarkHolder.getAssociatedBookMarksMap().size());
    }

}
