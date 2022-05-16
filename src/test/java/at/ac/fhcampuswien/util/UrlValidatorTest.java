package at.ac.fhcampuswien.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UrlValidatorTest {

    @Test
    public void shouldCreateUrlValidator() {
        Assertions.assertNotNull(new UrlValidator());
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.at", "https://orf.at", "https://www.facebook.com"})
    public void should_successfully_validate_url(String url) {
        Assertions.assertTrue(UrlValidator.validate(url));
    }
}
