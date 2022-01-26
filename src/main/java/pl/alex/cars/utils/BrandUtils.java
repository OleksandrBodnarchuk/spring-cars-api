package pl.alex.cars.utils;

import org.jsoup.select.Elements;
import pl.alex.cars.entity.Manufacturer;

import java.io.IOException;

/*
 * Class that helps to extract data for Brands
 */
public class BrandUtils {
    protected static void getBrands(Elements brandElements) throws IOException {
        for (int i = 0; i < brandElements.size() - 1; i++) {
            // MAIN PLAYER
            Manufacturer manufacturer = new Manufacturer();
            String brandName = brandElements.get(i).attr("title");
            manufacturer.setTitle(brandName);
            String brandLogo = brandElements.get(i).getElementsByAttribute("src").attr("src");
            manufacturer.setBrandLogo(brandLogo);
            // GETTING MODELS
            ModelUtils.getModels(brandElements, i, manufacturer);
        }
    }
}
