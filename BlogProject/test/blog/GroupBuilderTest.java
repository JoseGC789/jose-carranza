package blog;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupBuilderTest {
    private GroupBuilder builder;

    @Before
    public void setUp(){
        builder = new GroupBuilder();
        builder.setGroupName("test group");
        builder.buildGroup();
        builder.clear();
    }


    @Test
    public void setGroupNameShouldBeNullIfContained() {
        builder.setGroupName("test group");
        assertNull(builder.getGroupName());
    }
}