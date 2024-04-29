public class TextBlock {
    private String text;
    private Style style;

    public TextBlock(String text, Style style) {
        this.text = text;
        this.style = style;
    }

    public TextBlock(String textBlock, StyleFactory styleFactory) {
        this.text = textBlock.substring(textBlock.indexOf("<text>='")+8, textBlock.indexOf(", <style>"));
        this.style = styleFactory.getStyle(textBlock.substring(textBlock.indexOf("<style>=")+8, textBlock.indexOf("}")));
    }

    @Override
    public String toString() {
        return "TextBlock{" +
                "<text>='" + text + '\'' +
                ", <style>=" + style +
                '}';
    }
}
