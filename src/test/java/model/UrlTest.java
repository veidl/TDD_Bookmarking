package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UrlTest {

    @ParameterizedTest
    @ValueSource(strings = {"https://google.at", "https://orf.at", "www.facebook.com"})
    public void should_successfully_validate_url(String url) {
        Assertions.assertTrue(UrlValidator.validate(url));
    }
}
