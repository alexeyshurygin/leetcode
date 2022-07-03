import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Alexey Shurygin
 */
@Disabled
public class QSortTest {

    @Test
    public void qsortTest() {
        int[] a = {};
        QSort.qsort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void qsortTest2() {
        int[] a = {1};
        QSort.qsort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void qsortTest3() {
        int[] a = {1, 2};
        QSort.qsort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void qsortTest4() {
        int[] a = {2, 1};
        QSort.qsort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void qsortTest5() {
        int[] a = {2, 1, 3};
        QSort.qsort(a);
        System.out.println(Arrays.toString(a));
    }
}
