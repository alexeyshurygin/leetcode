import java.util.Arrays;

class QSort {
    public static void qsortInt(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(arr, left, right);
        if ((left < index - 1)) {
            qsortInt(arr, left, index - 1);
        }
        if (right > index) {
            qsortInt(arr, index, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int index = (right + left) / 2;
        int pivot = arr[index];
        while (left < right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (right > index && arr[right] >= pivot) {
                right--;
            }
            swap(arr, left, right);
        }
        return index;
    }

    private static void swap(int[] arr, int left, int right) {
        int x = arr[left];
        arr[left] = arr[right];
        arr[right] = x;
    }

    public static void qsort(int[] a) {
        qsortInt(a, 0, a.length - 1);
    }
}