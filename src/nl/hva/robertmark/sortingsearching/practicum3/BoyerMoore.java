package nl.hva.robertmark.sortingsearching.practicum3;

/**
 * Created by mark on 14-1-2017.
 */
public class BoyerMoore {
    private int[] right;
    private String pat;

    BoyerMoore(String pat) { // Compute skip table.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1; // -1 for chars not in pattern
        for (int j = 0; j < M; j++) // rightmost position for
            right[pat.charAt(j)] = j; // chars in pattern
    }

    public String search(String txt) { // Search for pattern in txt.
        int N = txt.length();
        int amountOfChecks = 0;
        int amountOfWords = 0;
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) { // Does the pattern match the text at position i ?
            skip = 0;
            for (int j = M - 1; j >= 0; j--)
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    amountOfChecks++;
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            if (skip == 0) {
                amountOfWords++;
                skip++;
            }
            // found.
        }
        return "BoyerMoore woord:: " + pat + " aantal woorden: " + amountOfWords + " Aantal checks: " + amountOfChecks;
    }
}