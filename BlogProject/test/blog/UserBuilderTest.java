package blog;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserBuilderTest {
    private UserBuilder builder;

    @Before
    public void setUp() {
        builder = new UserBuilder();
        builder.setUserName("test");
        builder.setUserPassword("test");
        builder.buildBlogUser();
        builder.clear();
    }

    @Test
    public void setUserNameShouldBeNullIfContainedOrEmpty() {
        builder.setUserName("test");
        assertNull(builder.getUserName());
        builder.setUserName("");
        assertNull(builder.getUserName());
        builder.setUserName(" ");
        assertNull(builder.getUserName());
    }

    @Test
    public void setUserPasswordShouldBeNullIfEmpty() {
        builder.setUserPassword("");
        assertNull(builder.getUserPassword());
        builder.setUserPassword(" ");
        assertNull(builder.getUserPassword());
    }
}