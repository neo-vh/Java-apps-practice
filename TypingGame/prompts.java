import java.util.*;

public class prompts {

    static String[] possibleWords = {
            "Java", "programming", "software", "developer", "code", "logic", "algorithm",
            "design", "learning", "practice", "debugging", "testing", "structure", "function",
            "object", "array", "loop", "class", "method", "variable", "string", "syntax",
            "system", "project", "solution", "innovation", "technology", "future", "quality",
            "challenge", "creative", "efficient", "reliable", "robust", "secure"
    };

    static Character[] possibleLetters = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String randomWords(int length) {
        String result = "";
        Random r = new Random();

        for (int i = 0; i < length; i++) {
            result = String.format("%s %s", result, possibleWords[r.nextInt(possibleWords.length)]);
        }

        return result;
    }

    public static String randomLetters(int itemLen, int totalItems) {

        Random r = new Random();

        String result = new String();

        for (int n = 0; n <= totalItems; n++) {
            String item = new String();
            for (int i = 0; i <= itemLen; i++) {
                item = item + possibleLetters[r.nextInt(possibleLetters.length)];
            }
            result = String.format("%s %s ", result, item);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(randomWords(10).strip());

        System.out.println(randomLetters(5, 5).strip());
    }

}