import java.util.List;
import java.util.Map;

public class ExporterApp {
    private static final String INIT_URL = "https://www.cars-data.com/en/";
    private final static String PATH = "C:\\Users\\oleksandrbodnarchuk\\Downloads\\cars\\";
    private static Map<String, String> cars = JsoupImageLinkExtractor.exportCarData(INIT_URL + "/car-brands-cars-logos.html");
    private final static List<String> brands = JsoupImageLinkExtractor.carBrands;

    public static void main(String[] args) {
        for (String car : brands) {
            System.out.println(car);
            cars = JsoupImageLinkExtractor.exportCarData("https://www.cars-data.com/en/" + car);
            cars.forEach((imageTitle, carUrl) -> JpgImageExporter.downloadImagesFromUrl(PATH, imageTitle, carUrl));
        }
    }
}
