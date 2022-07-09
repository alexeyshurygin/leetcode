/**
* Beats 100% of solutions of
* https://leetcode.com/problems/search-in-rotated-sorted-array/description/
*/
public class SortedArrayII {
public int search(final int[] n, final int t) {
if (n[0] <= t){
for (int i = 0; i < n.length; i++) {
if (n[i] == t)
return i;
if (n[i] > t)
return -1;
}} else if (n[n.length - 1] >= t) {
for (int i = n.length -1; i >= 0; i--) {
if (n[i] == t)
return i;
if (n[i] < t)
return -1;
}
}
return -1;
}
}
