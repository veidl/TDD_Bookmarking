package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.BookMarkHolder;
import at.ac.fhcampuswien.model.Bookmark;
import at.ac.fhcampuswien.model.CustomUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

class SerializationUtilTest {

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
    void shouldCreateUrlValidator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<SerializationUtil> constructor = SerializationUtil.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
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

    @Test
    void shouldThrowUnsupportedOperationExceptionIfNoBookmarksArePresent() {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> SerializationUtil.backup(new CustomUser("not gonna work")));
        Assertions.assertEquals("Cannot backup user", exception.getMessage());
    }

    @Test
    void shouldRestoreBackedUpUser() {
        CustomUser user = new CustomUser("oliver");
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        user.addBookmarkHolder(bookMarkHolder);
        SerializationUtil.backup(user);

        CustomUser restoredUser = SerializationUtil.restore(user.getId());

        Assertions.assertEquals(user.getUserName(), restoredUser.getUserName());
        Bookmark restoredBookmark = user.getMyBookmarks().getBookmarks().get(0);
        Assertions.assertNotNull(restoredBookmark);
        Assertions.assertEquals(restoredBookmark.getTimeStamp(), user.getMyBookmarks().getBookmarks().get(0).getTimeStamp());
        Assertions.assertEquals(restoredBookmark.getRating(), user.getMyBookmarks().getBookmarks().get(0).getRating());
        Assertions.assertEquals(restoredBookmark.getCustomUrl().getUrl(), user.getMyBookmarks().getBookmarks().get(0).getCustomUrl().getUrl());
        Assertions.assertEquals(restoredBookmark.getCustomUrl().getTag(), user.getMyBookmarks().getBookmarks().get(0).getCustomUrl().getTag());
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionIfNoUserWasBackedUpped() {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> SerializationUtil.restore(UUID.randomUUID()));
        Assertions.assertEquals("Cannot restore User", exception.getMessage());
    }
}
