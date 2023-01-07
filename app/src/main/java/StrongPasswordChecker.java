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

    private int getReplaceNumEx(String p, int start) {
        int delN = p.length() - start - 20;
        if (delN < 0)
            throw new AssertionError("delN < 0");
        var sb = new StringBuilder(p.substring(start));
        int r = 0;
        char prev = 0;
        int cnt = 0;
        int excessCnt = 0;
        int fullThreeCnt = 0;
        for (int i = 0; i < 20; ) {
            char c = sb.charAt(i);
            if (c == prev) {
                cnt++;
                i++;
            } else {
                excessCnt += cnt / 3;
                if (cnt >= 3 && cnt % 3 == 0) {
                    fullThreeCnt++;
                    sb.deleteCharAt(i - 1);
                } else {
                    i++;
                }
                cnt = 1;
                prev = c;
            }
        }
        if (sb.length() > 20)
            sb.delete(20, sb.length());
        excessCnt += cnt / 3;
        if (cnt >= 3 && cnt % 3 == 0) fullThreeCnt++;
        int toDelete = Math.min(delN, fullThreeCnt);
        delN -= toDelete;
        excessCnt -= toDelete;
        if (excessCnt < 0)
            throw new AssertionError("excessCnt < 0");
        if (delN < 0)
            throw new AssertionError("delN < 0");
        long l = sb.chars().filter(Character::isLowerCase).count();
        long u = sb.chars().filter(Character::isUpperCase).count();
        long d = sb.chars().filter(Character::isDigit).count();
        if (u < 1) r++;
        if (l < 1) r++;
        if (d < 1) r++;
        r = Math.max(r, excessCnt) + delN;
        System.out.printf("Password:%s sb:%s delN:%d, excessCnt:%d, r:%d\n", p, sb, delN, excessCnt, r);
        return r + start;
    }

    public int strongPasswordChecker(String p) {
        int min = 0;
        if (p.length() < 6) min = 6 - p.length();
        if (p.length() > 20) {
            int minR = Integer.MAX_VALUE;
            for (int l = 0; l <= p.length() - 20; l++) {
                int r = getReplaceNumEx(p, l);
                minR = Math.min(minR, r);
            }
            return minR;
        }
        int r = getReplaceNum(p);
        r = Math.max(r, min);
        return r;
    }
}
