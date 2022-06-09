package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.BookMarkHolder;
import at.ac.fhcampuswien.model.CustomUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

public class SerializationUtilTest {

    @Test
    void shouldBackupUser() throws Exception {
        CustomUser thomas = new CustomUser("thomas");

        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        thomas.addBookmarkHolder(bookMarkHolder);
        SerializationUtil.backup(thomas);

        File file = new File(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups/" + thomas.getId() + ".ser");

        Assertions.assertTrue(file.exists());
    }
}
