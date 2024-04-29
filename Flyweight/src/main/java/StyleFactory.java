import java.util.ArrayList;

public class StyleFactory {
    private ArrayList<Style> styles = new ArrayList<>();

    public Style getStyle(String color, String font, String size) {
        for (Style style : styles) {
            if (style.equals(color, font, size))
                return style;
        }
        Style newStyle = new Style(color, font, size);
        styles.add(newStyle);
        return newStyle;
    }

    public Style getStyle(String style) {
        String[] styleArray = style.split(", ");
        return getStyle(styleArray[0], styleArray[1], styleArray[2]);
    }
}
