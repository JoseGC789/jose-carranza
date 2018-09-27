package blog.search;

import blog.Entry;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilterRecentTest {
    private List<Searchable> filtered;
    private List<Searchable> original;

    @Before
    public void setUp(){
        FilterRecent filter;
        original = new ArrayList<>();
        original.add(new Entry(1));
        original.add(new Entry(2));
        original.add(new Entry(3));
        original.add(new Entry(4));
        original.add(new Entry(5));
        filter = new FilterRecent(original);
        filtered = filter.filter(2);
    }

    @Test
    public void filterCheckFilteredContained() {
        //contains
        assertTrue(filtered.contains(new Entry(5)));
        assertTrue(filtered.contains(new Entry(4)));
    }

    @Test
    public void filterCheckFilteredNotContained() {
        //doesn't contains
        assertFalse(filtered.contains(new Entry(3)));
        assertFalse(filtered.contains(new Entry(2)));
        assertFalse(filtered.contains(new Entry(1)));
    }

    @Test
    public void filterCheckOriginalIsUntouched() {
        //doesn't contains
        assertEquals(5,original.size());
    }
}