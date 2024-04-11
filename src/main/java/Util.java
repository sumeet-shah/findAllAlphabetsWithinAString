import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Util {
    public static String readFile(
            String fileName) throws FileNotFoundException {
        // Get the class loader
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        // Get the resource as an InputStream
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        // Check if file exists
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + fileName);
        }
        // Read the file content using Scanner or BufferedReader
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        }
        return content.toString();
    }

    public static boolean isValidChar(char currentCharacter) {
        return currentCharacter >= 'a' && currentCharacter <= 'z';
    }
}
