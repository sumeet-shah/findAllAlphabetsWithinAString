import org.apache.commons.lang3.time.StopWatch;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        String input = Util.readFile("input.txt");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        boolean answer = containsAllAlphabets(input);
        stopWatch.stop();
        System.out.println("contains all the alphabets? " + answer + ". It " +
                                   "took " + stopWatch.getTime() + "ms");
        System.out.println();
        System.out.println("starting with multithreading logic now");
        System.out.println();
        MultithreadingLogic multithreadingLogic = new MultithreadingLogic();
        stopWatch.reset();
        stopWatch.start();
        answer = multithreadingLogic.findAllAlphabets(
                input);
        stopWatch.stop();
        System.out.println(
                "contains all the alphabets? " + answer + ". It took " + stopWatch.getTime() + "ms");
    }
    private static Boolean containsAllAlphabets(String input) {
        // if input is null or length is less than 26 then of course answer
        // is false
        if (input == null || input.length() < 26) {
            return false;
        }
        // convert everything to lowercase to avoid confusion
        input = input.toLowerCase();
        // data structure to store and keep track of already found character
        Set<Character> foundAlphabets = new HashSet<>();
        // for each character
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            boolean isValidCharacter = Util.isValidChar(currentCharacter);
            if (isValidCharacter) {
                foundAlphabets.add(currentCharacter);
                if (foundAlphabets.size() == 26) return true;
            }
        }
        return false;
    }
}
