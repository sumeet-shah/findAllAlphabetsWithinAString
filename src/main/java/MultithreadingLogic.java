import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class MultithreadingLogic {

    AtomicReference<Set<Character>> tracker = new AtomicReference<>(
            new HashSet<>());

    public boolean findAllAlphabets(String input) {
        // corner cases validations
        if (input == null || input.length() < 26) {
            return false;
        }
        input = input.toLowerCase();
        // get the count of the available processors
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // int availableProcessors = 3;
        // divide the input string to number of chunks equal to the available
        // processor counts
        List<String> theChunks = getTheChunks(input, availableProcessors);
        List<CompletableFuture<Void>> futures = theChunks.stream()
                .map(x -> {
                    return CompletableFuture.runAsync(() -> {
                        if (tracker.get().size() == 26) return;
                        for (int i = 0; i < x.length(); i++) {
                            var currentCharacter = x.charAt(i);
                            boolean isValidCharacter =
                                    Util.isValidChar(currentCharacter);
                            if (isValidCharacter) {
                                tracker.get().add(currentCharacter);
                                if (tracker.get().size() == 26) return;
                            }
                        }
                    });
                }).toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .join();
        return tracker.get().size() == 26;
    }

    private static List<String> getTheChunks(String input,
                                             int availableProcessors) {
        List<String> chunks = new ArrayList<>();
        // length of each chunk
        int lengthOfChunks = input.length() / availableProcessors;
        int counter = 0;
        while (counter < input.length()) {
            int end = counter + lengthOfChunks;
            if (end > input.length()) {
                end = input.length();
            }
            chunks.add(input.substring(counter, end));
            counter += lengthOfChunks;
        }
        return chunks;
    }
}
