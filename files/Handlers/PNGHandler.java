package Handlers;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PNGHandler {

    /**
     * Load a PNG image from the filesystem.
     *
     * @param filePath Path to the PNG file (relative or absolute)
     * @return BufferedImage or null if loading fails
     */
    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("[ERROR][PNGHandler]: Failed to load image from path: " + filePath);
            return null;
        }
    }

    /**
     * Load a PNG image embedded in the JAR/resources folder.
     *
     * @param resourcePath Resource path, e.g., "/icons/icon.png"
     * @return BufferedImage or null
     */
    public static BufferedImage loadFromResources(String resourcePath) {
        try {
            URL url = PNGHandler.class.getResource(resourcePath);
            if (url == null) {
                System.err.println("[ERROR][PNGHandler]: Resource not found: " + resourcePath);
                return null;
            }
            return ImageIO.read(url);
        } catch (IOException e) {
            System.err.println("[ERROR][PNGHandler]: Failed to load resource image: " + resourcePath);
            return null;
        }
    }

    /**
     * Converts BufferedImage to ImageIcon for Swing components.
     *
     * @param image BufferedImage instance
     * @return ImageIcon or null
     */
    public static ImageIcon toIcon(BufferedImage image) {
        return image != null ? new ImageIcon(image) : null;
    }
}