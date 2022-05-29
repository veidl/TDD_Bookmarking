package at.ac.fhcampuswien.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomUserTest {

    @Test
    void shouldCreateUser() {
        CustomUser user = new CustomUser("init");
        Assertions.assertNotNull(user);
    }

    @Test
    void shouldCreateUserWithNameAndUniqueId() {
        CustomUser customUser = new CustomUser("thomas");

        Assertions.assertNotNull(customUser.getId());
        Assertions.assertEquals("thomas", customUser.getUserName());
    }
}
