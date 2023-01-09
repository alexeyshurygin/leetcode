import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/strong-password-checker
 *
 * @author Alexey Shurygin
 */
public class StrongPasswordChecker {
    private static char getDigit(int... chars) {
        if (Arrays.stream(chars).noneMatch(c -> c == '1'))
            return '1';
        if (Arrays.stream(chars).noneMatch(c -> c == '2'))
            return '2';
        if (Arrays.stream(chars).noneMatch(c -> c == '3'))
            return '3';
        throw new AssertionError();
    }

    private static char getUpper(int... chars) {
        if (Arrays.stream(chars).noneMatch(c -> c == 'A'))
            return 'A';
        if (Arrays.stream(chars).noneMatch(c -> c == 'B'))
            return 'B';
        if (Arrays.stream(chars).noneMatch(c -> c == 'C'))
            return 'C';
        throw new AssertionError();
    }

    private static char getLower(int... chars) {
        if (Arrays.stream(chars).noneMatch(c -> c == 'a'))
            return 'a';
        if (Arrays.stream(chars).noneMatch(c -> c == 'b'))
            return 'b';
        if (Arrays.stream(chars).noneMatch(c -> c == 'c'))
            return 'c';
        throw new AssertionError();
    }

    boolean strong(StringBuilder p) {
        if (!(p.length() >= 6 && p.length() <= 20 && p.chars().anyMatch(Character::isLowerCase) && p.chars().anyMatch(Character::isUpperCase) && p.chars().anyMatch(Character::isDigit)))
            return false;
        if (getNextChain(p, 0).length > 0) return false;
        return true;
    }

    private C getNextChain(StringBuilder p, int start) {
        char prev = 0;
        int cnt = 0;
        char c = 0;
        for (int i = start; i < p.length(); i++) {
            c = p.charAt(i);
            if (c == prev) {
                cnt++;
            } else {
                if (cnt >= 3)
                    return new C(prev, i - cnt, cnt);
                cnt = 1;
                prev = c;
            }
        }
        if (cnt < 3) return new C(-1, 0);
        return new C(c, p.length() - cnt, cnt);
    }

    public int bfNum(StringBuilder p) {
        System.out.printf("Initial pw:%s\n", p);
//      If less than 6: add 6-length chars
//      add only missing characters
//      add char/add missing char is the same
//      If more than 20: remove length-20 chars
//      remove breaking 3+ first
//      remove by not creating 3+
//      remove/break 3+ is the same
//      remove only non-missing chars
//      if there's 6<=length<=20 chars check for l u d if there's no - change/add
//      if there's more than 3 chars - change every 3rd char to another char
//      break 3+/add missing char is the same
        if (strong(p)) return 0;
        int nl = (int) p.chars().filter(Character::isLowerCase).count();
        int nu = (int) p.chars().filter(Character::isUpperCase).count();
        int nd = (int) p.chars().filter(Character::isDigit).count();
        D d = new D(nl, nu, nd);
        d = breakChains(d, p);
        while (p.length() > 20) {
            d = removeChars(d, p);
        }
        while (p.length() < 6) {
            d = addChar(d, p);
        }
        d = addMissing(d, p);
        System.out.printf("Result  pw:%s complexity:%d\n", p, d.n);
        return d.n;
    }

    D addChar(D d, StringBuilder p) {
        assert p.length() < 6;
        if (d.nl == 0) {
            p.append("a");
            return new D(d.n + 1, d.nl + 1, d.nu, d.nd);
        }
        if (d.nu == 0) {
            p.append("A");
            return new D(d.n + 1, d.nl, d.nu + 1, d.nd);
        }
        if (d.nd == 0) {
            p.append("1");
            return new D(d.n + 1, d.nl, d.nu, d.nd + 1);
        }
        char c = p.charAt(p.length() - 1);
        p.append(++c);
        return new D(d.n + 1, d.nl, d.nu, d.nd);

    }

    D removeChars(D d, StringBuilder p) {
        int n = d.n;
        int nl = d.nl;
        int nu = d.nu;
        int nd = d.nd;
        for (int i = p.length() - 1; p.length() > 20 && i >= 0; i--) {
            char ch = p.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (nu > 1) {
                    p.deleteCharAt(i);
                    nu--;
                    n++;
                }
            } else if (Character.isLowerCase(ch)) {
                if (nl > 1) {
                    p.deleteCharAt(i);
                    nl--;
                    n++;
                }
            } else if (Character.isDigit(ch)) {
                if (nd > 1) {
                    p.deleteCharAt(i);
                    nd--;
                    n++;
                }
            } else {
                p.deleteCharAt(i);
                n++;
            }
        }
        return new D(n, nl, nu, nd);
    }

    D breakChains(D d, StringBuilder p) {
        int n = d.n;
        int nl = d.nl;
        int nu = d.nu;
        int nd = d.nd;
        if (p.length() > 20) {
            // delete from chains
            C c;
            do {
                C maxC;
                for (maxC = c = getNextChain(p, 0); p.length() > 20 && c.length > 0; c = getNextChain(p, c.index + c.length - 1)) {
                    if (c.length % 3 != 0) {
                        if (maxC.length < c.length)
                            maxC = c;
                        continue;
                    }
                    if (Character.isLowerCase(c.ch))
                        nl--;
                    else if (Character.isUpperCase(c.ch))
                        nu--;
                    else if (Character.isDigit(c.ch))
                        nd--;
                    p.deleteCharAt(c.index);
                    n++;
                    if (maxC.length < c.length - 1)
                        maxC = new C(c.ch, c.index, c.length - 1);
                }
                if (p.length() > 20 && maxC.length > 0) {
                    if (Character.isLowerCase(maxC.ch))
                        nl--;
                    else if (Character.isUpperCase(maxC.ch))
                        nu--;
                    else if (Character.isDigit(maxC.ch))
                        nd--;
                    p.deleteCharAt(maxC.index);
                    n++;
                }
                c = getNextChain(p, 0);
            } while (p.length() > 20 && c.length > 0);
        }
//        aaaaabbbb1234567890ABA
//        aa1bb1b1234567890ABA
//        aa1aabb1234567890ABA
//        aa1bb1b1234567890ABA
        if (p.length() <= 20 && p.length() >= 6) {
            // change chars
            // avoid creating new chains by changing to equal adjacent character
            for (C c = getNextChain(p, 0); c.length > 0; c = getNextChain(p, c.index + c.length)) {
                for (int i = c.index + 2; i < c.index + c.length; i += 3) {
                    char ch;
                    if (nl == 0) {
                        ch = getLower(c.ch);
                        nl++;
                    } else if (nu == 0) {
                        ch = getUpper(c.ch);
                        nu++;
                    } else/* if (nd == 0) */ {
                        ch = getDigit(c.ch);
                        nd++;
                    }
                    p.setCharAt(i, ch);
                    n++;
                }
            }
        }
        if (p.length() < 6) {
            // Add chars
            // avoid creating new chains by changing to equal adjacent character
            for (C c = getNextChain(p, 0); c.length > 0; c = getNextChain(p, c.index + c.length)) {
                for (int i = c.index + 2; i < c.index + c.length; i += 3) {
                    char ch;
                    if (nl == 0) {
                        ch = getLower(c.ch);
                        nl++;
                    } else if (nu == 0) {
                        ch = getUpper(c.ch);
                        nu++;
                    } else/* if (nd == 0) */ {
                        ch = getDigit(c.ch);
                        nd++;
                    }
                    p.insert(i, ch);
                    n++;
                }
            }
        }
        return new D(n, nl, nu, nd);
    }

    D addMissing(D d, StringBuilder p) {
        assert p.length() >= 6 && p.length() <= 20;
        if (d.nl > 0 && d.nu > 0 && d.nd > 0) {
            return d;
        }
        int n = d.n;
        int nl = d.nl;
        int nu = d.nu;
        int nd = d.nd;
        if (nl == 0) {
            if (p.length() < 20) {
                p.append(getLower(p.charAt(p.length() - 1)));
            } else {
                for (int i = 0; i < p.length(); i++) {
                    char ch = p.charAt(i);
                    if (nu > 1 && Character.isUpperCase(ch) || nd > 1 && Character.isDigit(ch)) {
                        var chars = new ArrayList<Integer>();
                        chars.add((int) ch);
                        if (i > 0)
                            chars.add((int) p.charAt(i - 1));
                        if (i < p.length() - 1)
                            chars.add((int) p.charAt(i + 1));
                        char c = getLower(chars.stream().mapToInt(x -> x).toArray());
                        if (Character.isUpperCase(ch))
                            nu--;
                        if (Character.isDigit(ch))
                            nd--;
                        p.setCharAt(i, c);
                        break;
                    }
                }
            }
            nl++;
            n++;
        }
        if (nu == 0) {
            if (p.length() < 20) {
                p.append(getUpper(p.charAt(p.length() - 1)));
            } else {
                for (int i = 0; i < p.length(); i++) {
                    char ch = p.charAt(i);
                    if (nl > 1 && Character.isLowerCase(ch) || nd > 1 && Character.isDigit(ch)) {
                        var chars = new ArrayList<Integer>();
                        chars.add((int) ch);
                        if (i > 0)
                            chars.add((int) p.charAt(i - 1));
                        if (i < p.length() - 1)
                            chars.add((int) p.charAt(i + 1));
                        char c = getUpper(chars.stream().mapToInt(x -> x).toArray());
                        if (Character.isLowerCase(ch))
                            nl--;
                        if (Character.isDigit(ch))
                            nd--;
                        p.setCharAt(i, c);
                        break;
                    }
                }
            }
            nu++;
            n++;
        }
        if (nd == 0) {
            if (p.length() < 20) {
                p.append(getDigit(p.charAt(p.length() - 1)));
            } else {
                for (int i = 0; i < p.length(); i++) {
                    char ch = p.charAt(i);
                    if (nl > 1 && Character.isLowerCase(ch) || nu > 1 && Character.isUpperCase(ch)) {
                        var chars = new ArrayList<Integer>();
                        chars.add((int) ch);
                        if (i > 0)
                            chars.add((int) p.charAt(i - 1));
                        if (i < p.length() - 1)
                            chars.add((int) p.charAt(i + 1));
                        char c = getLower(chars.stream().mapToInt(x -> x).toArray());
                        if (Character.isLowerCase(ch))
                            nl--;
                        if (Character.isUpperCase(ch))
                            nu--;
                        p.setCharAt(i, c);
                        break;
                    }
                }
            }
            nd++;
            n++;
        }
        return new D(n, nl, nu, nd);
    }

    public int strongPasswordChecker(String p) {
        return bfNum(new StringBuilder(p));
    }

    record C(char ch, int index, int length) {
        C(int index, int length) {
            this((char) 0, index, length);
        }
    }

    record D(int n, int nl, int nu, int nd) {
        D(int nl, int nu, int nd) {
            this(0, nl, nu, nd);
        }
    }
}
