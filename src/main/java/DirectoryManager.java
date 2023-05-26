import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class DirectoryManager {

    public void createDirectory(Path path) {
        try {
            Files.createDirectories(path);
            System.out.println(path.getFileName()+" created successfully, path is: "+path.getParent());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the directory.");
            e.printStackTrace();
        }
    }

    public void deleteDirectory(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.delete()) {
                            System.out.println("Cannot delete: " + file);
                        }
                    });
            System.out.println(path.getFileName()+" deleted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the directory.");
            e.printStackTrace();
        }
    }

    public void displayDirectory(Path path) {
        try (Stream<Path> stream = Files.walk(path, 1)) {
            stream.forEach(file -> {
                System.out.println("File Name: " + file.getFileName());
                try {
                    System.out.println("File Size: " + Files.size(file) + " bytes");
                    System.out.println("Last Modified: " + Files.getLastModifiedTime(file));
                } catch (IOException e) {
                    System.out.println("An error occurred while getting the file details.");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("An error occurred while displaying the directory.");
            e.printStackTrace();
        }
    }
}

