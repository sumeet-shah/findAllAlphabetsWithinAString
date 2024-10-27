import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UsingParallelStreams implements PerformAction {

    @Override
    public boolean perform(String input) {
        //  if length is not even 26, nothing needs to be checked further
        if (input.length() < 26)
            return false;
        // convert string into character array
        Set<Character> collect = input
                .chars()
                .mapToObj(c -> (char) c)
                .parallel()
                .filter(c -> {
                    char charAt = c;
                    return (charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z');
                })
                .map(Character::toLowerCase)
                .collect(Collectors.toSet());
        System.out.println(collect);
        System.out.println("size: " + collect.size());
        if (collect.size() == 26) return true;
        return false;
    }
}
