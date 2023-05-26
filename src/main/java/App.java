import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        DirectoryManager directoryManager = new DirectoryManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to your new file manager \nYou'll never go back to your old GUI after experiencing this majesty!");
        System.out.println("While using your new file manager, please keep the following in mind: ");
        System.out.println("'Source path example' C:\\Users\\wowin\\OneDrive\\Pictures\\Deminei.jpg");
        System.out.println("'Destination path example' C:\\Users\\wowin\\OneDrive\\Pictures\\copy.jpg");
        System.out.println("'Directory path example' C:\\Users\\wowin\\OneDrive\\Pictures");

        System.out.println("\nPath's must be absolute and cannot be inside quotes. \nSorry for any inconvenience this may cause. \n;) Have fun!");

        while (true) {
            System.out.println("\nChoose an operation: \ncopy \nmove \ndelete \ncreate directory \ndelete directory \ndisplay directory \nsearch files \nexit ");
            String operation = scanner.nextLine().toLowerCase();

            if (operation.equals("exit")) {
                break;
            }

            switch (operation) {
                case "copy":
                    System.out.println("\nEnter path of file to copy: ");
                    String sourcePath = scanner.nextLine();
                    System.out.println("Enter destination path(including name and extension) of the copy: ");
                    String destPath = scanner.nextLine();
                    fileManager.copyFile(Paths.get(sourcePath), Paths.get(destPath));
                    break;

                case "move":
                    System.out.println("\nEnter path of the file to move: ");
                    sourcePath = scanner.nextLine();
                    System.out.println("Enter path destination you'd like to move this file to: ");
                    destPath = scanner.nextLine();
                    fileManager.moveFile(Paths.get(sourcePath), Paths.get(destPath));
                    break;

                case "delete":
                    System.out.println("\nEnter path of file to delete: ");
                    String filePath = scanner.nextLine();
                    fileManager.deleteFile(Paths.get(filePath));
                    break;

                case "create directory":
                    System.out.println("\nEnter path you would like to create the new directory to: ");
                    String dirPath = scanner.nextLine();
                    directoryManager.createDirectory(Paths.get(dirPath));
                    break;

                case "delete directory":
                    System.out.println("\nEnter directory path: ");
                    dirPath = scanner.nextLine();
                    directoryManager.deleteDirectory(Paths.get(dirPath));
                    break;

                case "display directory":
                    System.out.println("\nEnter directory path to display it's contents: ");
                    dirPath = scanner.nextLine();
                    directoryManager.displayDirectory(Paths.get(dirPath));
                    break;

                case "search files":
                    System.out.println("\nEnter the directory path to search for files in:");
                    String dirPathSearch = scanner.nextLine();
                    System.out.println("Enter the file name or extension type that your searching for:");
                    String searchTerm = scanner.nextLine();
                    List<String> foundFiles = fileManager.searchFiles(dirPathSearch, searchTerm);
                    if (foundFiles.isEmpty()) {
                        System.out.println("No files found matching your search term.");
                    } else {
                        System.out.println("Files found:");
                        for (String foundFile : foundFiles) {
                            System.out.println(foundFile);
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        }
        scanner.close();
    }
}


