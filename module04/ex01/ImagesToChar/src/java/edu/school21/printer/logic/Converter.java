package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Converter {
    private final char WHITE;
    private final char BLACK;
    private final BufferedImage IMAGE;

    public Converter(char white, char black) throws IOException {
        this.WHITE = white;
        this.BLACK = black;
        this.IMAGE = ImageIO.read(getClass().getResource("/resources/image.bmp"));
    }

    public void print() {
        for (int i = 0; i < IMAGE.getHeight(); i++) {
            for (int j = 0; j < IMAGE.getWidth(); j++) {
                int pixel = IMAGE.getRGB(j, i);
                if (pixel == Color.BLACK.getRGB()) {
                    System.out.print(BLACK);
                } else if (pixel == Color.WHITE.getRGB()) {
                    System.out.print(WHITE);
                }
            }
            System.out.println();
        }
    }
}
