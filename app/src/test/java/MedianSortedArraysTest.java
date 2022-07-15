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
    void testOdd0() {
        assertEquals(2, i.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    @Test
    void testOdd1() {
        assertEquals(2, i.findMedianSortedArrays(new int[]{1, 3, 7, 9}, new int[]{-22, -1, 2}));
    }

    @Test
    void testOdd2() {
        assertEquals(4, i.findMedianSortedArrays(new int[]{}, new int[]{-22, 4, 5}));
    }

    @Test
    void testOdd3() {
        assertEquals(4, i.findMedianSortedArrays(new int[]{-22, 4, 5}, new int[]{}));
    }

    @Test
    void testOdd4() {
        assertEquals(1, i.findMedianSortedArrays(new int[]{0, 1}, new int[]{-22, 4, 5}));
    }

    @Test
    void testOdd5() {
        assertEquals(5, i.findMedianSortedArrays(new int[]{-1, 1}, new int[]{2, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    void testOdd6() {
        assertEquals(5, i.findMedianSortedArrays(new int[]{2, 4, 5, 6, 7, 8, 9}, new int[]{-1, 1}));
    }

    @Test
    void testEven0() {
        assertEquals(2.5, i.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    @Test
    void testEven1() {
        assertEquals(1.5, i.findMedianSortedArrays(new int[]{1}, new int[]{2}));
    }

    @Test
    void testEven2() {
        assertEquals(1, i.findMedianSortedArrays(new int[]{}, new int[]{1}));
    }

    @Test
    void testEven3() {
        assertEquals(1, i.findMedianSortedArrays(new int[]{1}, new int[]{}));
    }

    @Test
    void testEven4() {
        assertEquals(2.5, i.findMedianSortedArrays(new int[]{}, new int[]{2, 3}));
    }

    @Test
    void testEven5() {
        assertEquals(2.5, i.findMedianSortedArrays(new int[]{2, 3}, new int[]{}));
    }

    @Test
    void testEven6() {
        assertEquals(4.5, i.findMedianSortedArrays(new int[]{2, 4, 5, 6, 7, 8,}, new int[]{-1, 1}));
    }
}
