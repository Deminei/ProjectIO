import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class FileManager {

    public void copyFile(Path sourcePath, Path destPath) {
        try {
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(sourcePath.getFileName() + " copied successfully to "+destPath.getFileName()+" in "+destPath.getParent());
        } catch (IOException e) {
            System.out.println("An error occurred while copying the file.");
            e.printStackTrace();
        }
    }

    public void moveFile(Path sourcePath, Path destPath) {
        try {
            Files.move(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(sourcePath.getFileName() + " moved successfully from "+sourcePath.getParent()+" to "+destPath.getParent());
        } catch (IOException e) {
            System.out.println("An error occurred while moving the file.");
            e.printStackTrace();
        }
    }

    public void deleteFile(Path path) {
        try {
            Files.delete(path);
            System.out.println( path.getFileName() + " deleted successfully from "+path.getParent());
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the file.");
            e.printStackTrace();
        }
    }

    public List<String> searchFiles(String dirPath, String search) {
        List<String> matchingFiles = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        String fileName = filePath.getFileName().toString();
                        if (fileName.toLowerCase().contains(search.toLowerCase())) {
                            matchingFiles.add(fileName);
                        }
                    });
        } catch (IOException e) {
            System.out.println("An error occurred while searching for files.");
            e.printStackTrace();
        }
        return matchingFiles;
    }
}
