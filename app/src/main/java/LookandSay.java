import java.util.ArrayList;

/**
 * @author Alexey Shurygin
 */
public class LookandSay {
    Integer[] seq(Integer[] a) {
        if (a == null || a.length == 0)
            return new Integer[0];
        ArrayList l = new ArrayList();
        int d = a[0], c = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == d) {
                c++;
            } else {
                l.add(c);
                l.add(d);
                d = a[i];
                c = 1;
            }
        }
        l.add(c);
        l.add(d);
        System.out.println(l);
        return (Integer[]) l.toArray(new Integer[l.size()]);
    }

    public static void main(String[] args) {
        LookandSay inst = new LookandSay();
        Integer[] arr = {1};
        for (int i = 0; i < 6; i++)
            arr = inst.seq(arr);
    }
}
