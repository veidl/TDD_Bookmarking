package at.ac.fhcampuswien.model;

public class Bookmark {

    private CustomUrl url;
    private int rating;

    public Bookmark() {
        this.rating = 0;
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
}
