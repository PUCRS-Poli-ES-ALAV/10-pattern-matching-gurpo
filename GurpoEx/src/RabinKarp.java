import java.util.ArrayList;
import java.util.Random;

public class RabinKarp {

    public static String generateString(int n) {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(n);
        final char[] alphabet = "A".toCharArray();
        for (int i = 0; i < n; i++) {
            sb.append(alphabet[rnd.nextInt(alphabet.length)]);
        }
        return sb.toString();
    }

    public static ArrayList<Integer> search(String pat, String txt) {
        int d = 256;
        int q = 101;
        int M = pat.length();
        int N = txt.length();
        int p = 0;
        int t = 0;
        int h = 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < M - 1; i++)
            h = (h * d) % q;
        for (int i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }
        for (int i = 0; i <= N - M; i++) {
            if (p == t) {
                boolean match = true;
                for (int j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    ans.add(i + 1); // 1-based indexing
            }
            // Calculate hash value for the next window
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
                // Ensure hash value is non-negative
                if (t < 0)
                    t += q;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String txt = generateString(50);
        String pat = txt.substring(13,42);
        ArrayList<Integer> res = search(pat, txt);
        for (int index : res)
            System.out.print(index + " ");
        System.out.println();
    }
}
