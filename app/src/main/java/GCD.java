// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
class GCD {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int generalizedGCD(int num, int[] arr) {
        // WRITE YOUR CODE HERE
        if (num <= 0) {
            return 0;
        }
        int gcd = arr[0];
        for (int i = 0; i < num; i++) {
            gcd = euclid(gcd, arr[i]);
        }
        return gcd;
    }

    int euclid(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        while (a != b) {
            if (a > b) {
                int t = b;
                b = a;
                a = t;
            }
            // a<=b
            b = b - a;
        }
        return a;
    }
    // METHOD SIGNATURE ENDS
}