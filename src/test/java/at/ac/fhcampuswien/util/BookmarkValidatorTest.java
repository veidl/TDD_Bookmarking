package at.ac.fhcampuswien.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookmarkValidatorTest {

    @Test
    public void shouldCreateBookmarkValidator() {
        Assertions.assertNotNull(new BookmarkValidator());
    }
}
