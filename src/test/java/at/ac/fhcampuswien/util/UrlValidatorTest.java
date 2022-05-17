package at.ac.fhcampuswien.util;

import at.ac.fhcampuswien.model.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;

class UrlValidatorTest {
    @Test
    void shouldCreateUrlValidator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UrlValidator> constructor = UrlValidator.class.getDeclaredConstructor();
        Assertions.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.at", "https://orf.at", "https://www.facebook.com"})
    void shouldSuccessfullyValidateUrl(String url) {
        Assertions.assertTrue(UrlValidator.validate(url));
    }

    @ParameterizedTest
    @ValueSource(strings = {"google.at", "https:/orf.at", "https//www.facebook.com"})
    void shouldFailForInvalidUrl(String url) {
        Assertions.assertFalse(UrlValidator.validate(url));
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.at", "https://orf.at", "https://www.facebook.com"})
    void shouldSuccessfullyValidateSecureUrl(String url) {
        Assertions.assertTrue(UrlValidator.isSecureUrl(url));
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://google.at", "ftp://orf.at", "file://www.facebook.com", "www.twitter.com"})
    void shouldFailForInvalidateSecureUrl(String url) {
        Assertions.assertFalse(UrlValidator.isSecureUrl(url));
    }
    @ParameterizedTest
    @CsvSource(value = "https://google.at,ftp://google.com")
    void shouldAssociateUrlBasedOnDomain(String a, String b) {
        Assertions.assertTrue(UrlValidator.isAssociatedWithAnyUrl(Collections.singletonList(a), b));
    }
}
