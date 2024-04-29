import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Document {
    private String title;
    private ArrayList<TextBlock> content;
    private final StyleFactory styleFactory;

    public Document(String title, ArrayList<TextBlock> content, StyleFactory styleFactory) {
        this.title = title;
        this.content = content;
        this.styleFactory = styleFactory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String saveFormat() {
        StringBuilder output = new StringBuilder();
        for (TextBlock textBlock : content) {
            output.append(textBlock.toString()).append("\n");
        }
        return output.toString();
    }

    public static Document loadFormat(File file, StyleFactory styleFactory) {
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<TextBlock> textBlocks = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            textBlocks.add(new TextBlock(fileReader.nextLine(), styleFactory));
        }

        return new Document(file.getName().replace(".fte", ""), textBlocks, styleFactory);
    }

    public static Document createDoc(String title, StyleFactory styleFactory){
        ArrayList<TextBlock> content = new ArrayList<>();
        return editDoc(new Document(title, content, styleFactory));
    }

    public static Document editDoc(Document doc){
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<TextBlock> content = doc.content;

        do {
            printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    content.add(newTextBlock(doc));
                    break;
                case "2":
                    System.out.println("Which text block? (enter number)");
                    int textBlockNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Copy the following text to edit it.\n");
                    System.out.println(content.get(textBlockNumber) + "\n");
                    System.out.println("Paste and edit the text below.\n");
                    String text = scanner.nextLine();
                    System.out.println("What is the style of your text block? (color, font, size)");
                    String style = scanner.nextLine();
                    content.remove(textBlockNumber);
                    content.add(textBlockNumber, new TextBlock(text, doc.styleFactory.getStyle(style)));
                    break;
                case "3":
                    for (int i = 0; i < content.size(); i++) {
                        System.out.println(i + ": " + content.get(i).toString() + "\n");
                    }
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
        while (!input.equals("4"));
        return new Document(doc.title, content, doc.styleFactory);
    }

    public static void printMenu(){
        System.out.println("Edit Doc Options (enter number): ");
        System.out.println("1. New Text Block");
        System.out.println("2. Edit Text Block");
        System.out.println("3. View Text Blocks");
        System.out.println("4. Exit");
    }

    public static TextBlock newTextBlock(Document doc) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the text of your text block?");
        String text = scanner.nextLine();
        System.out.println("What is the style of your text block? (color, font, size)");
        String style = scanner.nextLine();
        return new TextBlock(text, doc.styleFactory.getStyle(style));
    }
}
