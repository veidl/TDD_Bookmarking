package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.TestDataGenerator;
import at.ac.fhcampuswien.model.BookMarkHolder;
import at.ac.fhcampuswien.model.Bookmark;
import at.ac.fhcampuswien.model.CustomUser;
import org.junit.jupiter.api.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SerializationUtilTest {

    public static final File FILE = new File(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups");

    @BeforeAll
    static void initFolder() {
        FILE.mkdir();
    }

    @AfterAll
    static void cleanUp() {
        if(FILE.exists()) {
            Arrays.stream(Objects.requireNonNull(FILE.list())).forEach(file -> new File(FILE.getPath() + "/" + file).delete());
            FILE.delete();
        }
    }

    @Test
    void shouldCreateUrlValidator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<SerializationUtil> constructor = SerializationUtil.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    @Order(1)
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
        CustomUser user = new CustomUser("not gonna work");
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> SerializationUtil.backup(user));
        Assertions.assertEquals("Cannot backup user", exception.getMessage());
    }

    @Test
    @Order(2)
    void shouldRestoreBackedUpUser() {
        CustomUser user = new CustomUser("oliver");
        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        user.addBookmarkHolder(bookMarkHolder);
        SerializationUtil.backup(user);

        CustomUser restoredUser = SerializationUtil.restore(user.getId());

        Assertions.assertEquals(user.getUserName(), restoredUser.getUserName());
        Bookmark restoredBookmark = restoredUser.getMyBookmarks().getBookmarks().get(0);
        Assertions.assertNotNull(restoredBookmark);
        Assertions.assertEquals(restoredBookmark.getTimeStamp(), user.getMyBookmarks().getBookmarks().get(0).getTimeStamp());
        Assertions.assertEquals(restoredBookmark.getRating(), user.getMyBookmarks().getBookmarks().get(0).getRating());
        Assertions.assertEquals(restoredBookmark.getCustomUrl().getUrl(), user.getMyBookmarks().getBookmarks().get(0).getCustomUrl().getUrl());
        Assertions.assertEquals(restoredBookmark.getCustomUrl().getTag(), user.getMyBookmarks().getBookmarks().get(0).getCustomUrl().getTag());
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionIfNoUserWasBackedUpped() {
        UUID uuid = UUID.randomUUID();
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> SerializationUtil.restore(uuid));
        Assertions.assertEquals("Cannot restore User", exception.getMessage());
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionIfNoFileAlreadyExists() {
        CustomUser thomas = new CustomUser("thomas");

        BookMarkHolder bookMarkHolder = new BookMarkHolder();
        bookMarkHolder.addBookmark(TestDataGenerator.getValidBookmarkWithTag());

        thomas.addBookmarkHolder(bookMarkHolder);
        cleanUp();
        UnsupportedOperationException ex = Assertions.assertThrows(UnsupportedOperationException.class, () -> SerializationUtil.backup(thomas));
        Assertions.assertEquals("Cannot backup user", ex.getMessage());
    }
}
