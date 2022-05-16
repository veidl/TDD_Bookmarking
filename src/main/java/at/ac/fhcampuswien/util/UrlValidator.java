package at.ac.fhcampuswien.util;

import java.util.regex.Pattern;

public class UrlValidator {

    private static final String URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~|!:,.;]*[-a-zA-Z0-9+&@#/%=~|]";

    public static boolean validate(String url) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        return pattern.matcher(url).matches();
    }

}
