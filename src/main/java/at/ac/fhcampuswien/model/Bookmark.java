package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.util.UrlValidator;

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
