import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JpgImageExporter {


    public static void downloadImagesFromUrl(String path, String imageTitle, String carUrl) {
        try {
            BufferedImage image;

            // DOWNLOADING
            URL url = new URL(carUrl);
            // read the url
            image = ImageIO.read(url);

            // CREATE DIR PER MODEL
            File directory = new File(path + imageTitle);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // for jpg
            ImageIO.write(image, "jpg", new File(directory + "\\" + imageTitle + ".jpg"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}