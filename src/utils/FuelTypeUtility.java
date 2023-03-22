package utils;

import java.util.ArrayList;

public class FuelTypeUtility {

    private static ArrayList<String> fuelTypes = new ArrayList<String>(){{
        add("diesel");
        add("petrol");
    }};

    public static boolean validFuelType(String fuelType){
        return (fuelTypes.contains(fuelType));
    }
}
