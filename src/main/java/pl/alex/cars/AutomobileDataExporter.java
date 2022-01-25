package pl.alex.cars;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AutomobileDataExporter {


    public static final String MAIN_URL = "http://www.automobile-data.com/";

    public static void main(String[] args) {
        try {

            System.out.println("Connecting to main URL...");
            Document document = Jsoup.connect(MAIN_URL).get();
            Elements brandElements = document.getElementsByClass("mods-makes");

            for (int i = 0; i < brandElements.size() - 1; i++) {
                System.out.println("Brand tile: " + brandElements.get(i).attr("title"));
                Manufacturer manufacturer = new Manufacturer();
                String brandName = brandElements.get(i).attr("title");
                manufacturer.setTitle(brandName);
                System.out.println("Brand Link: " + MAIN_URL + brandElements.get(i).attr("href"));
                System.out.println("Picture Link: " + brandElements.get(i).getElementsByAttribute("src").attr("src"));
                String brandLogo = brandElements.get(i).getElementsByAttribute("src").attr("src");
                manufacturer.setBrandLogo(brandLogo);
                // GETTING MODELS
                Document models = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).get();
                Elements modelElements = models.getElementsByClass("mods-makes mods-models");
                for (Element modelElement : modelElements) {
                    System.out.println("Model title: " + modelElement.attr("title"));
                    System.out.println("Model link: " + MAIN_URL + modelElement.attr("href"));
                    System.out.println("Model Picture Link: " + MAIN_URL + modelElement.getElementsByAttribute("src").attr("src"));
                    Document modelSpec = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).get();

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
