package blog.search;

import blog.Entry;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilterRecentTest {
    private FilterRecent filter;
    private List<Searchable> searchables;

    @Before
    public void setUp(){
        searchables = new ArrayList<>();
        searchables.add(new Entry(1));
        searchables.add(new Entry(2));
        searchables.add(new Entry(3));
        searchables.add(new Entry(4));
        searchables.add(new Entry(5));
        filter = new FilterRecent(searchables);
    }

    @Test
    public void searchTest() {
        List<Searchable> expected;
        expected = filter.search(2);
        //contains
        assertTrue(expected.contains(new Entry(5)));
        assertTrue(expected.contains(new Entry(4)));
        //doesn't contains
        assertFalse(expected.contains(new Entry(3)));
        assertFalse(expected.contains(new Entry(2)));
        assertFalse(expected.contains(new Entry(1)));
    }
}