package pl.alex.cars.utils;

import org.jsoup.select.Elements;
import pl.alex.cars.entity.modification.Bodywork;
import pl.alex.cars.entity.modification.Variant;

import java.io.IOException;

public class VariantUtils extends ConnectionUtil{


    protected static Variant extractVariantData(String modificationVariantsLink) throws IOException {
        Variant variant = null;
        Bodywork bodywork;
        Elements variantsData =
                ConnectionUtil.getHtmlDocFromUrl(modificationVariantsLink)
                        .getElementsByClass("table_mods").get(1)
                        .getElementsByTag("span");

        for (int i = 2; i < variantsData.size(); i++) {
            if (i < 6) {
                bodywork = Bodywork.builder()
                        .bodyType(variantsData.get(i).text())
                        .numberOfDoors(Integer.parseInt(variantsData.get(++i).text()))
                        .numberOfSeats(Integer.parseInt(variantsData.get(++i).text()))
                        .luggageCapacity(variantsData.get(i).text())
                        .build();
                variant = Variant.builder()
                        .bodywork(bodywork)
                        .build();
            }

            System.out.println("  sadsds");
        }
        return variant;
    }

}
