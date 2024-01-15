package Game;

import java.awt.*;
import java.io.Serializable;

public class Item implements Serializable {
    private final Color color;
    private final int width;
    private final int height;
    private final boolean isDefault;
    public boolean isDefault() {
        return isDefault;
    }


    public Item(Color color, int width, int height, boolean isDefault) {
        this.isDefault = isDefault;
        this.color = color;
        this.width = width;
        this.height = height;
    }
    public Item(int width, int height, boolean isDefault){
        this.isDefault = isDefault;
        this.width = width;
        this.height = height;
        this.color = getRandColor();
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Item getDefaultItem(){
        return new Item(Color.lightGray,20,20, true);
    }

    private Color getRandColor(){
        int r = (int)(Math.random()*6);
        return switch(r){
            case 0 -> Color.RED;
            case 1 -> Color.ORANGE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.GREEN;
            case 4 -> Color.BLUE;
            default -> Color.MAGENTA;
        };
    }
}