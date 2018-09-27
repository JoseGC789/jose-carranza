package blog;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntryBuilderTest {
    private EntryBuilder builder = new EntryBuilder();

    @Test
    public void setTitleWithInputSize0() {
        builder.setTitle("");
        assertEquals("Untitled",builder.getTitle());
    }

    @Test
    public void setTextWithInputSize0() {
        builder.setText("");
        assertEquals("No text",builder.getText());
    }

    @Test
    public void buildEntryIdShouldIncrease() {
        builder.buildEntry();
        builder.buildEntry();
        int expected = 2;
        assertEquals(expected, EntryBuilder.getId());
    }
}