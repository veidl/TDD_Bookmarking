package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.BookMarkHolder;
import at.ac.fhcampuswien.model.CustomUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class SerializationUtilTest {

    public static final File FILE = new File(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups");
    @BeforeAll
    static void initFolder() {
        FILE.mkdir();
    }

    @AfterAll
    static void cleanUp() {
        Arrays.stream(Objects.requireNonNull(FILE.list())).forEach(file -> new File(FILE.getPath() + "/" + file).delete());
        FILE.delete();
    }

    @Test
    void shouldBackupUser() {
        CustomUser thomas = new CustomUser("thomas");

        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        thomas.addBookmarkHolder(bookMarkHolder);
        SerializationUtil.backup(thomas);

        File file = new File(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups/" + thomas.getId() + ".ser");

        Assertions.assertTrue(file.exists());
    }
}
