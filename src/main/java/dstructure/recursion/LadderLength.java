package dstructure.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Polylanger on 9/15/2017.
 */
public class LadderLength {

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dog");
        dict.add("dot");
        dict.add("lot");
        dict.add("log");
        LadderLength ladderLength = new LadderLength();
        System.out.println(ladderLength.ladderLength(start, end, dict));
    }

    /**
     * @param start: a string
     * @param end:   a string
     * @param dict:  a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        List<String> open = new ArrayList<String>(dict);
        List<String> close = new ArrayList<String>();
        close.add(start);

        int ladder = 1;
        while (!close.isEmpty()) {
            ladder += 1;

            int lastLayer = close.size();
            for (int i = 0; i < lastLayer; i++) {
                if (convertLength(close.get(i), end) == 1)
                    return ladder;
            }

            List<String> median = new ArrayList<String>();
            while (!open.isEmpty()) {
                String last = open.remove(open.size() - 1);
                boolean closed = false;
                for (int i = 0; i < lastLayer; i++) {
                    if (convertLength(close.get(i), last) <= 1) {
                        close.add(last);
                        closed = true;
                        break;
                    }
                }
                if (!closed) median.add(last);
            }

            open = median;
            close = close.subList(lastLayer, close.size());
        }

        return 0;
    }

    public int convertLength(String s1, String s2) {
        int differ = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                differ += 1;
            }
        }
        return differ;
    }

}
