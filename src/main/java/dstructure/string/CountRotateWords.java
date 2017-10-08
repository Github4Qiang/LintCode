package dstructure.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Polylanger on 10/3/2017.
 */
public class CountRotateWords {

    public static void main(String[] args) {
        CountRotateWords countRotateWords = new CountRotateWords();
        String[] array = {"picture", "turepic", "icturep", "word", "ordw", "lint"};
        System.out.println(countRotateWords.countRotateWords(Arrays.asList(array)));
    }

    /**
     * @param words: A list of words
     * @return: Return how many different rotate words
     */
    public int countRotateWords(List<String> words) {
        HashSet<String> table = new HashSet<String>();
        for (String word : words) {
            List<String> rotated = rotateWords(word);
            if (!containsWord(table, rotated)) {
                table.add(word);
            }
        }
        return table.size();
    }

    private boolean containsWord(HashSet<String> table, List<String> rotated) {
        for (String word : rotated) {
            if (table.contains(word))
                return true;
        }
        return false;
    }

    private List<String> rotateWords(String word) {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < word.length() - 1; i++) {
            String left = word.substring(0, i + 1);
            String right = word.substring(i + 1, word.length());
            words.add(right + left);
        }
        return words;
    }

}
