package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class BookmarkValidatorTest {

    @Test
    public void shouldCreateBookmarkValidator() {
        new BookmarkValidator();
    }

    @Test
    public void shouldSuccessfullyValidateBookmark() {
        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        List<Bookmark> bookmarks = Collections.singletonList(validBookmarkWithTag);
        Bookmark validBookmarkWithoutTag = TestDataGenerator.getValidBookmarkWithoutTag(validBookmarkWithTag.getCustomUrl().getUrl());

        int actual = BookmarkValidator.isValid(bookmarks, validBookmarkWithoutTag);

        Assertions.assertEquals(-1,actual);
    }

    @Test
    public void shouldNotValidateBookmark() {
        List<Bookmark> bookmarks = Collections.singletonList(TestDataGenerator.getValidBookmarkWithTag());
        Bookmark validBookmarkWithoutTag = TestDataGenerator.getValidBookmarkWithoutTag("https://orf.at");

        int actual = BookmarkValidator.isValid(bookmarks, validBookmarkWithoutTag);

        Assertions.assertEquals(0,actual);
    }
}
