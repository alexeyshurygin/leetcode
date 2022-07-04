import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexey Shurygin
 */
class MedianSortedArraysTest {
    private MedianSortedArrays i;

    @BeforeEach
    void setUp() {
        i = new MedianSortedArrays();
    }

    @Test
    void findMedianSortedArrays0() {
        assertEquals(2, i.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    void findMedianSortedArrays1() {
        assertEquals(2.5, i.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
