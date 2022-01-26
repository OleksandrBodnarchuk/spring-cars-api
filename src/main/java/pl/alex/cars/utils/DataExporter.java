package pl.alex.cars.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// BRAND HAS MODELS.
// EACH MODEL HAS IT'S MODIFICATION.
// EACH MODIFICATION HAS MULTIPLE ENGINES
// EACH ENGINE HAS IT'S TECHNICAL SPECIFICATION

public class DataExporter {

    // List with data - to be changed with Repository
    private static final List<Manufacturer> manufacturers = new ArrayList<>();

    public static void gatherData() throws IOException {
        System.out.println("Loading data from URL.\nThis may take a while.");
        Document document;
        document = ConnectionUtil.getHtmlDocFromUrl(ConnectionUtil.MAIN_URL);
        Elements brandElements = document.getElementsByClass("mods-makes");
        // GETTING BRANDS AND CONNECTED INFO
        BrandUtils.getBrands(brandElements);
        System.out.println(manufacturers);
    }

}
