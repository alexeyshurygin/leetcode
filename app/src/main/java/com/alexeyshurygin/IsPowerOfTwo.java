package com.alexeyshurygin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/power-of-two/
 *
 * @author Alexey Shurygin
 */
public class IsPowerOfTwo {
    //    public boolean isPowerOfTwo(int b) {
//        return isPowerOfTwoLoop(b);
//    }
    public boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        return (n > 0 && ((n & (n - 1)) == 0));
    }

    public boolean isPowerOfTwoCompare(int b) {
        return (b & 0b1) == (b | 0b1)
                || (b & 0b10) == (b | 0b10)
                || (b & 0b100) == (b | 0b100)
                || (b & 0b1000) == (b | 0b1000)
                || (b & 0b10000) == (b | 0b10000)
                || (b & 0b100000) == (b | 0b100000)
                || (b & 0b1000000) == (b | 0b1000000)
                || (b & 0b10000000) == (b | 0b10000000)
                || (b & 0b100000000) == (b | 0b100000000)
                || (b & 0b1000000000) == (b | 0b1000000000)
                || (b & 0b10000000000) == (b | 0b10000000000)
                || (b & 0b100000000000) == (b | 0b100000000000)
                || (b & 0b1000000000000) == (b | 0b1000000000000)
                || (b & 0b10000000000000) == (b | 0b10000000000000)
                || (b & 0b100000000000000) == (b | 0b100000000000000)
                || (b & 0b1000000000000000) == (b | 0b1000000000000000)
                || (b & 0b10000000000000000) == (b | 0b10000000000000000)
                || (b & 0b100000000000000000) == (b | 0b100000000000000000)
                || (b & 0b1000000000000000000) == (b | 0b1000000000000000000)
                || (b & 0b10000000000000000000) == (b | 0b10000000000000000000)
                || (b & 0b100000000000000000000) == (b | 0b100000000000000000000)
                || (b & 0b1000000000000000000000) == (b | 0b1000000000000000000000)
                || (b & 0b10000000000000000000000) == (b | 0b10000000000000000000000)
                || (b & 0b100000000000000000000000) == (b | 0b100000000000000000000000)
                || (b & 0b1000000000000000000000000) == (b | 0b1000000000000000000000000)
                || (b & 0b10000000000000000000000000) == (b | 0b10000000000000000000000000)
                || (b & 0b100000000000000000000000000) == (b | 0b100000000000000000000000000)
                || (b & 0b1000000000000000000000000000) == (b | 0b1000000000000000000000000000)
                || (b & 0b10000000000000000000000000000) == (b | 0b10000000000000000000000000000)
                || (b & 0b100000000000000000000000000000) == (b | 0b100000000000000000000000000000)
                || (b & 0b1000000000000000000000000000000) == (b | 0b1000000000000000000000000000000);
    }

    public boolean isPowerOfTwoLoop(int b) {
        while (b > 1) {
            if ((b & 0b1) == 1)
                return false;
            b >>= 1;
        }
        return b == 1;
    }

    private static final int[] V = new int[31];

    static {
        for (int i = 0, v = 1; i < 31; i++) {
            V[i] = v;
            v *= 2;
        }
    }

    public boolean isPowerOfTwoArray(int b) {
        return Arrays.binarySearch(V, b) >= 0;
    }

    private static final HashSet<Integer> HS = new HashSet<>();

    static {
        for (int i = 0, v = 1; i < 31; i++) {
            HS.add(v);
            v *= 2;
        }
    }

    public boolean isPowerOfTwoHashSet(int b) {
        return HS.contains(b);
    }

    private static final TreeSet<Integer> TS = new TreeSet<>();

    static {
        for (int i = 0, v = 1; i < 31; i++) {
            TS.add(v);
            v *= 2;
        }
    }

    public boolean isPowerOfTwoTreeSet(int b) {
        return TS.contains(b);
    }
}
