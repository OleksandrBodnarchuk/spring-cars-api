package pl.alex.cars.utils;

import org.jsoup.select.Elements;
import pl.alex.cars.entity.chasis.Chassis;
import pl.alex.cars.entity.dimentions.Dimensions;
import pl.alex.cars.entity.engine.Engine;
import pl.alex.cars.entity.fuelConsumption.FuelConsumption;
import pl.alex.cars.entity.modification.Bodywork;
import pl.alex.cars.entity.modification.Variant;
import pl.alex.cars.entity.transmission.Transmission;

import java.io.IOException;

public class VariantUtils extends ConnectionUtil {


    protected static Variant extractVariantData(String modificationVariantsLink) throws IOException {
        Variant variant;
        Bodywork bodywork = null;
        Engine engine = null;
        Transmission transmission = null;
        Dimensions dimensions = null;
        Chassis chassis = null;
        FuelConsumption fuelConsumption = null;
        Elements variantsData = ConnectionUtil.getHtmlDocFromUrl(modificationVariantsLink).select("table").get(1).getElementsByTag("span");
        // TODO: FIX IndexOutOfBoundsException  ERROR
        bodywork = getBodywork(variantsData);
        engine = getEngine(variantsData);
        transmission = getTransmission(variantsData);
        dimensions = getDimensions(variantsData);
        chassis = getChassis(variantsData);
        fuelConsumption = getFuelConsumption(variantsData);
        variant = Variant.builder()
                .bodywork(bodywork)
                .engine(engine)
                .transmission(transmission)
                .dimensions(dimensions)
                .chassis(chassis)
                .fuelConsumption(fuelConsumption)
                .build();

        return variant;
    }

    private static FuelConsumption getFuelConsumption(Elements variantsData) {
        int index = 29;
        return FuelConsumption.builder()
                .urban(variantsData.get(index).text())
                .extraUrban(variantsData.get(++index).text())
                .combined(variantsData.get(++index).text())
                .cO2Emissions(variantsData.get(++index).text())
                .fuelTankCapacity(variantsData.get(++index).text())
                .build();
    }

    private static Chassis getChassis(Elements variantsData) {
        int index = 23;
        return Chassis.builder()
                .frontSuspension(variantsData.get(index).text())
                .rearSuspension(variantsData.get(++index).text())
                .frontBrakes(variantsData.get(++index).text())
                .rearBrakes(variantsData.get(++index).text())
                .frontTires(variantsData.get(++index).text())
                .rearTires(variantsData.get(++index).text())
                .build();
    }

    private static Dimensions getDimensions(Elements variantsData) {
        int index = 18;
        return Dimensions.builder()
                .length(variantsData.get(index).text())
                .width(variantsData.get(++index).text())
                .height(variantsData.get(++index).text())
                .weight(variantsData.get(++index).text())
                .wheelBase(variantsData.get(++index).text())
                .build();
    }

    private static Transmission getTransmission(Elements variantsData) {
        int index = 15;
        return Transmission.builder()
                .driveWheels(variantsData.get(index).text())
                .steering(variantsData.get(++index).text())
                .gearbox(variantsData.get(++index).text())
                .build();
    }

    private static Engine getEngine(Elements variantsData) {
        String[] cylindersAndValves = variantsData.get(11).text().split("/");
        String[] powerBhpKwRpm = variantsData.get(12).text().split("/");
        return Engine.builder()
                .fuel(variantsData.get(6).text())
                .fuelSystem(variantsData.get(7).text())
                .engineType(variantsData.get(8).text())
                .enginePosition(variantsData.get(9).text())
                .engineCapacityCc(variantsData.get(10).text())
                .cylinders(cylindersAndValves[0])
                .valves(cylindersAndValves[1])
                .powerOutputBhp(powerBhpKwRpm[0])
                .powerOutputKw(powerBhpKwRpm[1])
                .powerOutputRpm(powerBhpKwRpm[2])
                .accelerationTo100(variantsData.get(13).text())
                .topSpeed(variantsData.get(14).text())
                .build();
    }

    private static Bodywork getBodywork(Elements variantsData) {
        int index = 2;
        return Bodywork.builder()
                .bodyType(variantsData.get(index).text())
                .numberOfDoors(variantsData.get(++index).text())
                .numberOfSeats(variantsData.get(++index).text())
                .luggageCapacity(variantsData.get(++index).text())
                .build();
    }

}
