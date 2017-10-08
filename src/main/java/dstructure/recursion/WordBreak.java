package dstructure.recursion;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Polylanger on 10/5/2017.
 */
public class WordBreak {

    public static void main(String[] args) {
        String s = "LintCode";
        Set<String> dict = new HashSet<>();
        dict.add("Lint");
        dict.add("Code");
        System.out.println(new WordBreak().wordBreak(s, dict));
    }

    /**
     * @param s:    A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] table = new boolean[s.length()+1];
        table[0] = true;

        for(int i = 0; i < s.length(); i++){
            for(String word : dict){
                if(!table[i]) continue;

                int end = i + word.length();
                if(end <= s.length() && s.substring(i, end).equals(word)){
                    table[end] = true;
                }
            }
        }
        return table[s.length()];
    }


}
