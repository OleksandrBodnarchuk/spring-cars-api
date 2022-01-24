import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JsoupImageLinkExtractor {

    public static List<String> carBrands = new ArrayList<>();

    public static Map<String, String> exportCarData(String url) {
        Map<String, String> carsMap = new HashMap<>();
        Document document;
        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get();

            //Get images from document object.
            Elements images =
                    document.select("img[src~=(?i)\\.(jpe?g)]"); // png||gif available as well

            //Iterate images and print image attributes.
            for (Element image : images) {
                String imageURL = image.attr("src");
                String imageTitle = image.attr("title");

                imageURL = checkUrlLinks(imageURL);
                imageTitle = modifyImageNaming(imageTitle);

                carsMap.put(imageTitle, imageURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carsMap;
    }

    private static String checkUrlLinks(String imageURL) {
        if (!imageURL.contains("http")) {
            imageURL = "https://www.cars-data.com/" + imageURL;
        }
        return imageURL;
    }

    private static String modifyImageNaming(String imageTitle) {
        if (imageTitle.contains(" logo")) {
            imageTitle = imageTitle.substring(0, imageTitle.indexOf(" "));
            populateBrands(imageTitle);
        }
        return imageTitle;
    }

    private static void populateBrands(String imageTitle) {
        imageTitle = imageTitle.toLowerCase(Locale.ROOT);
        if (imageTitle.contains(" ")) {
            imageTitle.replace(" ", "-");
        }
        carBrands.add(imageTitle);
    }
}
