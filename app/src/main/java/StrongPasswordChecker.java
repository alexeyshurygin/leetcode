/**
 * @author Alexey Shurygin
 */
public class StrongPasswordChecker {
    private int getReplaceNum(String p) {
        int r = 0;
        long l = p.chars().filter(Character::isLowerCase).count();
        long u = p.chars().filter(Character::isUpperCase).count();
        long d = p.chars().filter(Character::isDigit).count();
        char prev = 0;
        int cnt = 0;
        int excessCnt = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == prev) {
                cnt++;
            } else {
                excessCnt += cnt / 3;
                cnt = 1;
                prev = c;
            }
        }
        excessCnt += cnt / 3;
        System.out.printf("Password:%s, excessCnt:%d\n", p, excessCnt);
        if (u < 1) r++;
        if (l < 1) r++;
        if (d < 1) r++;
        r = Math.max(r, excessCnt);
        return r;
    }

    private int getReplaceNumEx(String p, int delN) {
        if (delN == 0)
            throw new IllegalArgumentException("delN is 0");
        int r = 0;
        long l = p.chars().filter(Character::isLowerCase).count();
        long u = p.chars().filter(Character::isUpperCase).count();
        long d = p.chars().filter(Character::isDigit).count();
        char prev = 0;
        int cnt = 0;
        int excessCnt = 0;
        int fullThreeCnt = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == prev) {
                cnt++;
            } else {
                excessCnt += cnt / 3;
                if (cnt >= 3 && cnt % 3 == 0) fullThreeCnt++;
                cnt = 1;
                prev = c;
            }
        }
        excessCnt += cnt / 3;
        if (cnt >= 3 && cnt % 3 == 0) fullThreeCnt++;
        int toDelete = Math.min(delN, fullThreeCnt);
        delN -= toDelete;
        excessCnt -= toDelete;
        if (excessCnt < 0)
            throw new AssertionError("excessCnt < 0");
        if (delN < 0)
            throw new AssertionError("delN < 0");
        if (u < 1) r++;
        if (l < 1) r++;
        if (d < 1) r++;
        r = Math.max(r, excessCnt) + delN;
        System.out.printf("Password:%s, delN:%d, excessCnt:%d, r:%d\n", p, delN, excessCnt, r);
        return r;
    }

    public int strongPasswordChecker(String p) {
        int min = 0;
        if (p.length() < 6) min = 6 - p.length();
        if (p.length() > 20) {
            int minR = Integer.MAX_VALUE;
            for (int l = 0; l <= p.length() - 20; l++) {
                String cut = p.substring(l, l + 20);
                int r = getReplaceNumEx(cut, p.length() - 20);
                minR = Math.min(minR, r);
            }
            return minR;
        }
        int r = getReplaceNum(p);
        r = Math.max(r, min);
        return r;
    }
}
