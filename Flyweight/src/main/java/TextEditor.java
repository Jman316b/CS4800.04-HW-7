import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextEditor {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./docs/");
        if (!file.exists()) {
            file.mkdir();
        }
        StyleFactory styleFactory = new StyleFactory();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Options (enter number): ");
        System.out.println("1. Create Doc");
        System.out.println("2. Edit Doc");
        System.out.println("3. View Docs List");
        System.out.println("4. Exit");

        String input = scanner.nextLine();
        while (!input.equals("4")) {
            switch (input) {
                case "1":
                    System.out.println("What is your document title?");
                    input = scanner.nextLine();
                    StoreDoc(Document.createDoc(input, styleFactory));
                    break;
                case "2":
                    System.out.println("What is the document title?");
                    input = scanner.nextLine();
                    removeAndReplaceDoc(input, styleFactory);
                    break;
                case "3":
                    viewDocList();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
            System.out.println("Options (enter number): ");
            System.out.println("1. Create Doc");
            System.out.println("2. Edit Doc");
            System.out.println("3. View Docs List");
            System.out.println("4. Exit");
            input = scanner.nextLine();
        }
        System.out.println("Have a good day!");
    }

    public static void StoreDoc(Document doc){
        try {
            FileWriter myWriter = new FileWriter("./docs/" + doc.getTitle() + ".fte"); //.fte -> flyweight text editor
            myWriter.write(doc.saveFormat());
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeAndReplaceDoc(String title, StyleFactory styleFactory) throws FileNotFoundException {
        File file = new File("./docs/" + title + ".fte");
        if(file.exists()){
            Document newDoc = Document.editDoc(Document.loadFormat(file, styleFactory));
            if(file.delete()){
                StoreDoc(newDoc);
            }
        }
        else {
            System.out.println("Doc does not exist, would you like to create it? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("y")) {
                System.out.println("What is your document title?");
                input = scanner.nextLine();
                StoreDoc(Document.createDoc(input, styleFactory));
            }
        }
    }

    public static void viewDocList(){
        System.out.println("Documents:");
        File file = new File("./docs/");
        if(file.exists()) {
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    String extension = f.getName().substring(f.getName().lastIndexOf("."));
                    if (extension.equals(".fte")) {
                        System.out.println(f.getName());
                    }
                }
            }
            System.out.println();
        }
        else
            System.out.println("None\n");
    }
}
