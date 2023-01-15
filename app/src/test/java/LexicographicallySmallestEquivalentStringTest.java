import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alexey Shurygin
 */
class LexicographicallySmallestEquivalentStringTest {
    LexicographicallySmallestEquivalentString i;

    @BeforeEach
    void setUp() {
        i = new LexicographicallySmallestEquivalentString();
    }

    @Test
    void smallestEquivalentString() {
        assertEquals("makkek", i.smallestEquivalentString("parker", "morris", "parser"));
        assertEquals("hdld", i.smallestEquivalentString("hello", "world", "hold"));
        assertEquals("", i.smallestEquivalentString("hello", "world", ""));
        assertEquals("h", i.smallestEquivalentString("hello", "world", "h"));
        assertEquals("d", i.smallestEquivalentString("hello", "world", "o"));
        assertEquals("cdx", i.smallestEquivalentString("hello", "world", "cox"));
        assertThrows(NullPointerException.class, () -> i.smallestEquivalentString(null, "", ""));
        assertThrows(NullPointerException.class, () -> i.smallestEquivalentString("", null, ""));
        assertThrows(NullPointerException.class, () -> i.smallestEquivalentString("", "", null));
    }
}
