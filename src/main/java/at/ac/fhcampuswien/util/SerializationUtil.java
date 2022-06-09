package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.model.CustomUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SerializationUtil {

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
}
