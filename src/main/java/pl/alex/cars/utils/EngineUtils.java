package pl.alex.cars.utils;

import org.jsoup.select.Elements;
import pl.alex.cars.entity.engine.Engine;

import java.util.ArrayList;
import java.util.List;

/*
 * Class that helps to extract data for Engines
 */
public class EngineUtils {

    protected static List<Engine> engineDataMapper(Elements modifications, int i) {
        List<String> engineData = extractModificationData(modifications, i);
        final List<Engine> engines = new ArrayList<>();
        Engine engine;
        for (int j = 0; j < engineData.size(); j = j + 5) {
            engine = Engine.builder()
                    .type(engineData.get(j))
                    .power(engineData.get(j + 1))
                    .fuel(engineData.get(j + 2))
                    .body(engineData.get(j + 3))
                    .yearOfProduction(engineData.get(j + 4))
                    .build();
            engines.add(engine);
        }
        return engines;
    }

    private static List<String> extractModificationData(Elements modifications, int i) {
        return modifications.get(i).getElementsByAttributeValueStarting("href", "?modification_id=").eachText();
    }
}
