package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

class BookmarkValidatorTest {

    @Test
    void shouldCreateBookmarkValidator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<BookmarkValidator> constructor = BookmarkValidator.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void shouldSuccessfullyValidateBookmark() {
        Bookmark validBookmarkWithTag = TestDataGenerator.getValidBookmarkWithTag();
        List<Bookmark> bookmarks = Collections.singletonList(validBookmarkWithTag);
        Bookmark validBookmarkWithoutTag = TestDataGenerator.getValidBookmarkWithoutTag(validBookmarkWithTag.getCustomUrl().getUrl());

        int actual = BookmarkValidator.isValid(bookmarks, validBookmarkWithoutTag);

        Assertions.assertEquals(0, actual);
    }

    @Test
    void shouldNotValidateBookmark() {
        List<Bookmark> bookmarks = Collections.singletonList(TestDataGenerator.getValidBookmarkWithTag());
        Bookmark validBookmarkWithoutTag = TestDataGenerator.getValidBookmarkWithoutTag("https://orf.at");

        int actual = BookmarkValidator.isValid(bookmarks, validBookmarkWithoutTag);

        Assertions.assertEquals(-1, actual);
    }
}
