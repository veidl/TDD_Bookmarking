package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomUrlTest {


    @Test
    public void shouldCreateCustomUrl() {
        CustomUrl customUrl = new CustomUrl();
        Assertions.assertNotNull(customUrl);
    }
}
