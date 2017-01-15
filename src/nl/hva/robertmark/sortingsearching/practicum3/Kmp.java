package nl.hva.robertmark.sortingsearching.practicum3;

/**
 * Created by mark on 14-1-2017.
 */
public class Kmp {
    private String pat;
    private int[][] dfa;

    public Kmp(String pat) { // Build DFA from pattern.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) { // Compute dfa[][j].
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X]; // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j + 1; // Set match case.
            X = dfa[pat.charAt(j)][X]; // Update restart state.
        }
    }

    public String search(String txt) { // Simulate operation of DFA on txt.
        int amountOfChecks = 0;
        int amountOfWords = 0;

        int M = pat.length();
        int N =txt.length();
        int i, j;
        for(i=0, j = 0; i < N && j < M; i++){
            j = dfa[txt.charAt(i)][j];
            amountOfChecks++;
            if(j == M){
                amountOfWords++;
                j=0;
            }
        }
        return "KMP WOORD: " + pat + " aantal woorden: " + amountOfWords + " Aantal vergelijkinge: " + amountOfChecks;
    }

    // See page 769.
}
