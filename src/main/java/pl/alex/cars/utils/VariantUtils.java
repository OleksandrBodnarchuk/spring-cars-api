package pl.alex.cars.utils;

import org.jsoup.select.Elements;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.modification.Bodywork;
import pl.alex.cars.entity.modification.Variant;

import java.io.IOException;

public class VariantUtils extends ConnectionUtil {


    protected static Variant extractVariantData(String modificationVariantsLink) throws IOException {
        Variant variant;
        Bodywork bodywork;
        Engine engine;

        Elements variantsData =
                ConnectionUtil.getHtmlDocFromUrl(modificationVariantsLink)
                        .getElementsByClass("table_mods").get(1)
                        .getElementsByTag("span");
        bodywork = getBodywork(variantsData);
        engine = getEngine(variantsData);

        variant = Variant.builder()
                .bodywork(bodywork)
                .engine(engine)
                .build();
        System.out.println("  sadsds");
        return variant;
    }

    private static Engine getEngine(Elements variantsData) {

        int index = 6;
        return Engine.builder()
                .fuel(variantsData.get(index).text())
                .fuelSystem(variantsData.get(++index).text())
                .engineType(variantsData.get(++index).text())
                .enginePosition(variantsData.get(++index).text())
                .engineCapacityCc(Integer.parseInt(variantsData.get(++index).text()))
                .cylinders(Integer.parseInt(variantsData.get(++index).text().substring(0,1)))
                .valves(Integer.parseInt(variantsData.get(index).text().substring(2)))
                .powerOutputBhp(Integer.parseInt(variantsData.get(++index).text().substring(0,3)))
                .powerOutputKw(Integer.parseInt(variantsData.get(index).text().substring(4,7)))
                .powerOutputRpm(Integer.parseInt(variantsData.get(index).text().substring(8)))
                .accelerationTo100(Float.parseFloat(variantsData.get(++index).text()))
                .topSpeed(Integer.parseInt(variantsData.get(++index).text().substring(0,3)))
                .build();
    }

    private static Bodywork getBodywork(Elements variantsData) {
        int index = 2;
        return Bodywork.builder()
                .bodyType(variantsData.get(index).text())
                .numberOfDoors(Integer.parseInt(variantsData.get(++index).text()))
                .numberOfSeats(Integer.parseInt(variantsData.get(++index).text()))
                .luggageCapacity(variantsData.get(++index).text())
                .build();
    }

}
