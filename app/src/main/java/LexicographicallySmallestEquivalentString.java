import java.util.HashMap;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
 *
 * @author Alexey Shurygin
 */
public class LexicographicallySmallestEquivalentString {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Objects.requireNonNull(baseStr);
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        if (s1.length() != s2.length()) throw new IllegalArgumentException("Lengths do not match");
        var chars = new HashMap<Character, SortedSet<Character>>();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            var set1 = chars.get(c1);
            var set2 = chars.get(c2);
            if (set1 == null)
                if (set2 == null) {
                    set2 = new TreeSet<>();
                    set2.add(c1);
                    set2.add(c2);
                    chars.put(c1, set2);
                    chars.put(c2, set2);
                } else {
                    set2.add(c1);
                    chars.put(c1, set2);
                }
            else {
                if (set2 == null) {
                    set1.add(c2);
                    chars.put(c2, set1);
                } else {
                    //Merge
                    set1.addAll(set2);
                    for (Character c : set2) {
                        chars.put(c, set1);
                    }
                }
            }
        }
        var r = new StringBuilder(s1.length());
        for (int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);
            SortedSet<Character> clazz = chars.get(c);
            if (clazz != null)
                c = clazz.first();
            r.append(c);
        }
        return r.toString();
    }
}
