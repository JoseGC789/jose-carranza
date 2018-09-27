package day1stuff;

import org.junit.Test;
import static org.junit.Assert.*;

public class SticksCounterTest {
    private SticksCounter counter = new SticksCounter();

    @Test
    public void removeSticksWithAvailability() {
        boolean actual = counter.removeSticks(2);
        assertEquals(true , actual);
    }

    @Test
    public void removeSticksWithoutAvailability() {
        boolean actual = counter.removeSticks(56);
        assertEquals(false , actual);
    }
}
