//package Labb1_package;

/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fĆ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords2 {
    LinkedList<String> closestWords = null;
    final static int M[][] = new int[41][41];
    static int i;
    static int j;
    static int a;
    static int min;
    static String latestWord;
    static int latestWordLen;
    static int sLen;
    static int jStart;
    LinkedList<String> wordList;


    int closestDistance = -1;
    static int dist;

    int partDist(String word, String wListWord, int wordLen, int wListWordLen) {
        jStart = 1;

        for (i = 0; i < Math.min(latestWordLen, wListWordLen); i++) {
            if (latestWord.charAt(i) != wListWord.charAt(i)) {
                break;
            }
            jStart ++;
        }

        for (j = jStart ; j < wListWordLen + 1; j++) {
            for (i = 1; i < wordLen + 1; i++) {
                a = 1;
                if (word.charAt(i-1) == wListWord.charAt(j-1))
                    a = 0;
                M[i][j] = Math.min(a + M[i-1][j-1], Math.min(1 + M[i-1][j], 1 + M[i][j-1]));
            }
        }
        return M[wordLen][wListWordLen];
    }

    int[][] getM() {
        return M;
    }

    public ClosestWords2(LinkedList<String> wordList) {
        this.wordList = wordList;
        for (i = 0; i < 41; i++) {
            M[i][0] = i;
        }
        for (j = 0; j < 41; j++) {
            M[0][j] = j;
        }
        closestWords = new LinkedList<String>();


    }

    public void calculate(String w) {
        latestWord = "";
        latestWordLen = 0;
        closestDistance = 55;
        int wlen = w.length();

        for (String s : wordList) {
            sLen = s.length();
            dist = partDist(w, s, wlen, sLen);
            latestWord = s;
            latestWordLen = sLen;
            if (dist < closestDistance ) {
                closestDistance = dist;
                closestWords.clear();
                closestWords.add(s);
            }
            else if (dist == closestDistance)
                closestWords.add(s);
        }

    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }


}

