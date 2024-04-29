public class Style {
    private String color;
    private String font;
    private String size;

    public Style(String color, String font, String size) {
        this.color = color;
        this.font = font;
        this.size = size;
    }

    public Style(String style){
        String[] styleArray = style.split(", ");
        this.color = styleArray[0];
        this.font = styleArray[1];
        this.size = styleArray[2];
    }

    @Override
    public String toString() {
        return color.toString() + ", " + font.toString() + ", " + size.toString();
    }

    public boolean equals(String color, String font, String size) {
        return this.color.equals(color) && this.font.equals(font) && this.size.equals(size);
    }
}
