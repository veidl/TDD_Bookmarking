package at.ac.fhcampuswien.model;

import java.util.UUID;

public class CustomUser {

    private UUID id;
    private String userName;
    private BookMarkHolder myBookmarks;

    public CustomUser(String userName) {
        this.id = UUID.randomUUID();
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public BookMarkHolder getMyBookmarks() {
        return this.myBookmarks;
    }

    public void addBookmarkHolder(BookMarkHolder bookMarkHolder) {
        this.myBookmarks = bookMarkHolder;
    }
}
