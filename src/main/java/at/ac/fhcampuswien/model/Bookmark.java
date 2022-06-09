package at.ac.fhcampuswien.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public class Bookmark implements Serializable {

    private final transient Supplier<LocalDateTime> supplier;
    private CustomUrl url;
    private int rating;
    private LocalDateTime timeStamp;

    public Bookmark() {
        this(LocalDateTime::now);
        this.rating = 0;
    }

    public Bookmark(final Supplier<LocalDateTime> supplier) {
        this.supplier = supplier;
        this.addTime();
    }

    public int getRating() {
        return rating;
    }

    public void increaseRating() {
        this.rating++;
    }

    public CustomUrl getCustomUrl() {
        return url;
    }

    public void setCustomUrl(CustomUrl url) {
        this.url = url;
    }

    public void addTime() {
        this.timeStamp = supplier.get();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
