package pl.alex.cars;

import java.io.IOException;

public class AutomobileDataExporter {


    public static void main(String[] args) {
        try {
            DataExporter.gatherData();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}

