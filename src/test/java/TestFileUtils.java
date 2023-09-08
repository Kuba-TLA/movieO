
import java.io.*;
import java.nio.file.Files;

public class TestFileUtils {
    public static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    public static boolean directoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory();
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();

    }
}