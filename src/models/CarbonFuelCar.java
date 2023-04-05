package models;

import utils.FuelTypeUtility;
import utils.Utilities;

public class CarbonFuelCar extends Car {

    private float fuelConsumption = 5;

    private float carbonEmission  = 1;

    private boolean automatic = false;

    private String fuelType = "petrol";

    private int engineSize = 800;

    public CarbonFuelCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int secs0To60, int topSpeed, float torque, String fuelType, float fuelConsumption, float carbonEmission, int engineSize, boolean automatic) {
        super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
        setFuelConsumption(fuelConsumption);
        setCarbonEmission(carbonEmission);
        setAutomatic(automatic);
        setFuelType(fuelType);
        setEngineSize(engineSize);
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        if(Utilities.validRange(fuelConsumption, 5, 20, 0))
            this.fuelConsumption = fuelConsumption;
    }

    public float getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(float carbonEmission) {
        if(carbonEmission > 0)
            this.carbonEmission = carbonEmission;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
            this.automatic = automatic;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        if(FuelTypeUtility.validFuelType(fuelType))
            this.fuelType = fuelType;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        if(Utilities.validRange(engineSize, 600, 2500))
            this.engineSize = engineSize;
    }

    public double getCarbonFootPrint() {
        return (engineSize * fuelConsumption * carbonEmission * super.getAge()) / 2000;
    }
}
