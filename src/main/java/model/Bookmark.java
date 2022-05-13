package model;

public class Bookmark {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!UrlValidator.validate(url)) {
            throw new IllegalArgumentException("Not a valid URL");
        }
        this.url = url;
    }
}
