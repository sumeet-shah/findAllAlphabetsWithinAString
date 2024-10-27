import org.apache.commons.lang3.time.StopWatch;

import java.util.HashSet;
import java.util.Set;

public class WithoutThreads implements PerformAction {
    @Override
    public boolean perform(String input) {
        System.out.println(
                "sync method : length of the input: " + input.length());
        StopWatch started = StopWatch.createStarted();
        // if input is null or length is less than 26 then of course answer
        // is false
        if (input.length() < 26) {
            return false;
        }
        // data structure to store and keep track of already found character
        Set<String> foundAlphabets = new HashSet<>();
        // for each character
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            boolean isValidCharacter = Util.isValidChar(currentCharacter);
            if (isValidCharacter) {
                foundAlphabets.add(
                        String.valueOf(currentCharacter).toLowerCase());
                if (foundAlphabets.size() == 26) return true;
            }
        }
        started.stop();
        System.out.println("total time taken : " + started.getTime());
        return false;
    }
}
