package blog;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticatorTest {
    private Authenticator authenticator;
    private User testUser;

    @Before
    public void setUp() {
        authenticator = new Authenticator();
        testUser = new User("TestUser1");
        User.getBuilder().getUserRepository().add((new User("TestUser1","password")));
    }

    @Test
    public void authenticateWithNonExistentUser() {
        authenticator.setUserName("NonUser");
        assertNull(authenticator.authenticate());
    }

    @Test
    public void authenticateWithWrongPassword() {
        authenticator.setUserName("TestUser1");
        authenticator.setUserPassword("Wrong");
        assertNull(authenticator.authenticate());
    }

    @Test
    public void authenticateWithCorrectFields() {
        authenticator.setUserName("TestUser1");
        authenticator.setUserPassword("password");
        assertEquals(testUser,authenticator.authenticate());
    }
}