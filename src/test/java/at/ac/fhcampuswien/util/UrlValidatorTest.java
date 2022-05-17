package at.ac.fhcampuswien.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

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
    void should_successfully_validate_url(String url) {
        Assertions.assertTrue(UrlValidator.validate(url));
    }
}
