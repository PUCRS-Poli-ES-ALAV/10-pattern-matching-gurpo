import java.util.Random;

public class App {
    public static long instructions = 0;

    public static void main(String[] args) throws Exception {
        String stringLonga = generateString(500000);
        System.out.println(hFind("A",
                "AHASHAJHDJBSDHFGHASGDHASGFHAGFHAGSFHAGFHAGAVABJADSHFKASHDGFKJASHFKASHFKJAHSFKJHASFKHASFKJHASF"));
    }

    public static int hFind(String pattern, String text) {
        int M = pattern.length();
        System.out.println("Pattern Length: " + M);
        int N = text.length();
        System.out.println("Text Length: " + N);
        long patternHash = hash(pattern, M);

        instructions += 3;
        for (int i = 0; i <= N - M; i++) {
            instructions++;
            long txtHash = hash(text.substring(i, i + M), M);
            instructions++;
            if (patternHash == txtHash) {
                instructions++;
                System.out.println("Text Hash: " + txtHash);
                System.out.println("Pattern Hash: " + patternHash);
                System.out.println("Instrucoes: " + instructions);
                return i;
            }
        }
        System.out.println("Instrucoes: " + instructions);
        return -1;
    }

    public static long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (31 * h + key.charAt(j)) % 997;
        }
        return h;
    }

    public static int find(String pattern, String text) {
        instructions = instructions + 3;
        try {
            for (int i = 0; i < text.length() - pattern.length(); i++) {
                instructions++;

                if (text.charAt(i) == pattern.charAt(0)) {
                    instructions++;
                    if (text.substring(i, i + pattern.length()).equals(pattern)) {
                        instructions++;
                        System.out.println("Instructions:" + instructions);
                        return i;
                    }
                }
            }
            System.out.println("Instructions:" + instructions);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds reached.");
            return -1;
        }
        return -1;

    }

    public static String generateString(int n) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(n);
        final char[] alphabet = "A".toCharArray();
        for (int i = 0; i < n; i++) {
            sb.append(alphabet[rnd.nextInt(alphabet.length)]);
        }
        return sb.toString();
    }

    public static String stringCreation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            sb.append("A");
        }
        return sb.toString();
    }
}