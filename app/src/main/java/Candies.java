import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * @author Alexey Shurygin
 */
public class Candies {
    public static void main(String[] args) throws IOException {
//        2
//7 3
//1 2 3 4 5 6 7
//5 10
//7 7 7 7 7
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var s = reader.readLine();
        var tc = Integer.parseInt(s);
        for (int i = 0; i < tc; i++) {
            s = reader.readLine();
            String[] strings = s.split("\\s");
            var bags = Integer.parseInt(strings[0]);
            var kids = Integer.parseInt(strings[1]);
            s = reader.readLine();
            String[] candies = s.split("\\s");
            var sumn = 0;
            for (int j = 0; j < bags; j++) {
                sumn += Integer.parseInt(candies[j]);
            }
            System.out.printf("Case #%d: %d\n", i+1, sumn % kids);
        }
    }
}
