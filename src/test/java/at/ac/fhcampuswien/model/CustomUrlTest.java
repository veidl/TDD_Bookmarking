package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CustomUrlTest {


    @Test
    public void shouldCreateCustomUrl() {
        CustomUrl customUrl = new CustomUrl();
        Assertions.assertNotNull(customUrl);
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://orf.at", "https://twitter.com", "https://www.google.at"})
    public void shouldAddValidUrl(String url) {
        CustomUrl customUrl = new CustomUrl();

        customUrl.setUrl(url);

        Assertions.assertEquals(url, customUrl.getUrl());
    }
}
