package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookmarkValidatorTest {

    @Test
    public void shouldCreateBookmarkValidator() {
        Assertions.assertNotNull(new BookmarkValidator());
    }

    @Test
    public void shouldSuccessfullyValidateBookmark() {
        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        Bookmark validBookmarkWithoutTag = TestDataGenerator.getValidBookmarkWithoutTag();

        boolean actual = BookmarkValidator.validate(validBookmarkWithoutTag, validBookmarkWithTag);

        Assertions.assertTrue(actual);

    }
}
