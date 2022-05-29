package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomUserTest {

    @Test
    void shouldCreateUser() {
        CustomUser user = new CustomUser();
        Assertions.assertNull(user);
    }
}
