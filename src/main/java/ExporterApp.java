import java.util.Map;

public class ExporterApp {
    private final static String PATH = "C:\\Users\\oleksandrbodnarchuk\\Downloads\\cars\\";
    private final static Map<String, String> cars = JsoupImageLinkExtractor.exportCarData("URL");

    public static void main(String[] args) {
        cars.forEach((title, fileUrl) -> JpgImageExporter.downloadImagesFromUrl(PATH, title, fileUrl));
    }
}
