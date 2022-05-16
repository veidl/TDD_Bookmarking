package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookMarkHolderTest {

    @Test
    public void shouldCreateBookMarkHolder() {
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        Assertions.assertNotNull(bookMarkHolder);
    }
}
