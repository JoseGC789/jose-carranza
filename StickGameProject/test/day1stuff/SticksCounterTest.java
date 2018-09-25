package day1stuff;

import org.junit.Test;
import static org.junit.Assert.*;

public class SticksCounterTest {
    private SticksCounter counter = new SticksCounter();

    @Test
    public void removeSticksWithAvailability() {
        boolean expected = true;
        boolean actual = counter.removeSticks(2);
        assertTrue(expected || actual);
    }

    @Test
    public void removeSticksWithoutAvailability() {
        boolean expected = false;
        boolean actual = counter.removeSticks(56);
        assertFalse(expected || actual);
    }
}
