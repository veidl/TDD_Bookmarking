package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CustomUrlTest {


    @Test
    void shouldCreateCustomUrl() {
        CustomUrl customUrl = new CustomUrl();
        Assertions.assertNotNull(customUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://orf.at", "https://twitter.com", "https://www.google.at"})
    void shouldAddValidUrl(String url) {
        CustomUrl customUrl = new CustomUrl();

        customUrl.setUrl(url);

        Assertions.assertEquals(url, customUrl.getUrl());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidUrl() {
        CustomUrl customUrl = new CustomUrl();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> customUrl.setUrl("notAValidUrl"));
        Assertions.assertEquals("Not a valid URL", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"testTag1", "123", "!$%§$!", "null"})
    void shouldAddTagToUrl(String tag) {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setTag(tag);

        Assertions.assertEquals(tag, customUrl.getTag());
    }

    @Test
    void shouldRemoveURL() {
        CustomUrl customUrl = new CustomUrl();
        customUrl.setUrl("https://facebook.com");

        customUrl.removeURL();
        Assertions.assertNull(customUrl.getUrl());
    }
}
