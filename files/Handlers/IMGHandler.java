package Handlers;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import basicHandler.DataHandler;
import utils.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IMGHandler extends DataHandler {
	
	private final String IMGLOC = super.DATALOC + "img" + File.separator;
	private static Logger logger;
	
	private Exception failedToFindPath = new Exception("Path matches no files in data");

	public IMGHandler() {
		logger = new Logger(this.getClass());
	}
	
    /**
     * Load a PNG image from the filesystem.
     *
     * @param filePath Path to the PNG file (relative or absolute)
     * @return BufferedImage or null if loading fails
     */
    public BufferedImage loadImage(String type, String imgName) {
    	logger.logInfo("Trying to load img type : " + type + " named :" + imgName);
    	String ressourcePath = getRessourcePath(type, imgName);
        try {
        	BufferedImage image = ImageIO.read(new File(ressourcePath));
        	ressourcePath = null;
        	logger.logInfo("Loaded img type : " + type + " named :" + imgName);
            return image;
        } catch (IOException e) {
            logger.logError("Failed to load image from type : " + type + " named :" + imgName, e);
        	ressourcePath = null;
            return null;
        }
    }

    /**
     * Load a PNG image embedded in the JAR/resources folder.
     *
     * @param resourcePath Resource path, e.g., "/icons/icon.png"
     * @return BufferedImage or null
     */
    public BufferedImage loadFromResources(String pathName) {
    	logger.logInfo("Trying to load img from pathName : " + pathName);
        try {
            URL url = IMGHandler.class.getResource(pathName);
            if (url == null) {
            	logger.logError("Resource not found: " + pathName, failedToFindPath);
                return null;
            }
        	logger.logInfo("Loaded img from pathName : " + pathName);
            return ImageIO.read(url);
        } catch (IOException e) {
            logger.logError("Failed to load resource image from pathname : " + pathName, e);
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
    	Logger.staticLog("Converting awt.BufferedImage to swing.ImageIcon");
        return image != null ? new ImageIcon(image) : null;
    }
    
    private String getRessourcePath(String type, String imgName) {
			return IMGLOC + type + File.separator + imgName + "." + type;
    }
}