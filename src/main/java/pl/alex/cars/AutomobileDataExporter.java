package pl.alex.cars;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDataExporter {

    public static final String MAIN_URL = "http://www.automobile-data.com/";

    static List<Manufacturer> manufacturers = new ArrayList<>();

    public static void main(String[] args) {
        try {

            System.out.println("Connecting to main URL...");
            Document document = Jsoup.connect(MAIN_URL).timeout(10 * 10000).get();
            Elements brandElements = document.getElementsByClass("mods-makes");

            // Looping through main page and gathering all brands
            for (int i = 0; i < brandElements.size() - 1; i++) {
                Manufacturer manufacturer = new Manufacturer();
                String brandName = brandElements.get(i).attr("title");
                manufacturer.setTitle(brandName);
                String brandLogo = brandElements.get(i).getElementsByAttribute("src").attr("src");
                manufacturer.setBrandLogo(brandLogo);

                // GETTING MODELS
                Document models = Jsoup.connect(MAIN_URL + brandElements.get(i).attr("href")).get();
                Elements modelElements = models.getElementsByClass("mods-makes mods-models");
                for (Element modelElement : modelElements) {
                    Model model = new Model();
                    model.setTitle(modelElement.attr("title"));
                    model.setPictureLink(MAIN_URL + modelElement.getElementsByAttribute("src").attr("src"));

                    String modelLink = MAIN_URL + modelElement.attr("href");
                    Document modificationsDoc = Jsoup.connect(modelLink).get();
                    Elements modificationElements = modificationsDoc.getElementsByAttributeValue("name", "modifications");
                    Modification modification = new Modification();

                    // GETTING MODEL MODIFICATIONS
                    for (Element modificationElement : modificationElements) {
                        Elements modelModifications = modificationElement.getElementsByClass("modification-group-name");
                        Elements modelModificationPhoto = modificationElement.getElementsByClass("modification-group-photo");

                        for (int j = 0; j < modelModifications.size(); j++) {
                            String name = modelModifications.get(j).getElementsByTag("span").text();
                            modification.setName(name);
                            String modificationPicture = MAIN_URL + modelModificationPhoto.get(j).getElementsByTag("img").attr("src");
                            modification.setPictureLink(modificationPicture);
                        }
                        model.addModification(modification);
                        manufacturer.addModel(model);
                        System.out.println(manufacturer);

                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

