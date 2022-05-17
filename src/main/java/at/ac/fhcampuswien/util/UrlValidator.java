package at.ac.fhcampuswien.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator {
    private UrlValidator() {
        // intentionally empty
    }

    public static boolean validate(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static boolean isSecureUrl(String url) {
        return url.startsWith("https");
    }

    public static boolean isAssociatedWithAnyUrl(String oldUrl, String newUrl) {
        try {
            return new URL(oldUrl).getHost().equals(new URL(newUrl).getHost());
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
