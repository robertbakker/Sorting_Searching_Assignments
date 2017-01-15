package nl.hva.robertmark.sortingsearching.practicum3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by mark on 14-1-2017.
 */
public class MainPracticum3 {
    public static void main(String[] args) throws IOException {

        Rime rime = new Rime();
        String path = (new File("")).getAbsolutePath();
        String text = rime.getString(path + "/rijm.txt", Charset.defaultCharset());

        String[] words = {"verd're", "'t", "Raakten", "boomenlichtgetoover", "de", "ging", "Menschestemmen"};

        for (int i = 0; i < words.length; i++) {
            BoyerMoore boyerMoore = new BoyerMoore(words[i]);

            System.out.println(boyerMoore.search(text));

            Kmp kmp = new Kmp(words[i]);
            System.out.println(kmp.search(text));
            System.out.println();

        }


    }
}
