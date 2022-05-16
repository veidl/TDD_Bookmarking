package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.util.UrlValidator;

public class Bookmark {

    private CustomUrl url;

    public CustomUrl getCustomUrl() {
        return url;
    }

    public void setCustomUrl(CustomUrl url) {
        if (!UrlValidator.validate(url.getUrl())) {
            throw new IllegalArgumentException("Not a valid URL");
        }
        this.url = url;
    }
}
