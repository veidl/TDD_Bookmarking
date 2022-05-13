package bookmark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookmarkTest {

    @Test
    public void shouldCreateBookMark() {
        Bookmark bookmark = new Bookmark();
        Assertions.assertNotNull(bookmark);
    }
}
