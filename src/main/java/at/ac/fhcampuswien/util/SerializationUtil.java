package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.model.BookMarkHolder;
import at.ac.fhcampuswien.model.Bookmark;
import at.ac.fhcampuswien.model.CustomUser;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SerializationUtil {

    private SerializationUtil() {
        // intentionally empty
    }

    public static void backup(CustomUser user) {

        File file = new File(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups/" + user.getId() + ".ser");

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            file.createNewFile();
            oos.writeUTF(user.getUserName());
            oos.writeObject(user.getMyBookmarks().getBookmarks());

        } catch (Exception e) {
            throw new UnsupportedOperationException("Cannot backup user");
        }
    }

    public static CustomUser restore(UUID id) {
        try (FileInputStream fis = new FileInputStream(Objects.requireNonNull(SerializationUtil.class.getClassLoader().getResource(".")).getFile() + "/backups/" + id.toString() + ".ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return recreateUser(ois.readUTF(), (List<Bookmark>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new UnsupportedOperationException("Cannot restore User");
        }
    }

    private static CustomUser recreateUser(String name, List<Bookmark> bookmarks) {
        CustomUser customUser = new CustomUser(name);
        BookMarkHolder bookMarkHolder = new BookMarkHolder();

        bookmarks.forEach(bookMarkHolder::addBookmark);

        customUser.addBookmarkHolder(bookMarkHolder);

        return customUser;
    }
}
