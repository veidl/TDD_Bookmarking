package at.ac.fhcampuswien.model;

import at.ac.fhcampuswien.util.UrlValidator;

public class CustomUrl {

    String url;
    String tag;

    public CustomUrl() {
        this.url = null;
        this.tag = null;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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
