package models;

import utils.FuelTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class CarbonFuelCar extends Car {

    private float fuelConsumption = 5;

    private float carbonEmission = 1;

    private boolean automatic = false;

    private String fuelType = "petrol";

    private int engineSize = 800;

    public CarbonFuelCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int secs0To60, int topSpeed, float torque, String fuelType, float fuelConsumption, float carbonEmission, int engineSize, boolean automatic) {
        super(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque);
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
            this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarbonFuelCar that = (CarbonFuelCar) o;
        return Float.compare(that.getFuelConsumption(), getFuelConsumption()) == 0 && Float.compare(that.getCarbonEmission(), getCarbonEmission()) == 0 && isAutomatic() == that.isAutomatic() && getEngineSize() == that.getEngineSize() && Objects.equals(getFuelType(), that.getFuelType());
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        if(Utilities.validRange(engineSize, 800, 2500))
            this.engineSize = engineSize;
    }

    public double getCarbonFootPrint() {
        return (engineSize * 1000 * fuelConsumption * carbonEmission * super.getAge()) / 2000;
    }

    @Override
    public String toString() {
        return super.toString() +
                " fuelConsumption: " + fuelConsumption +
                ", carbonEmission: " + carbonEmission +
                ", automatic: " + automatic +
                ", fuelType: " + fuelType +
                ", engineSize: " + engineSize;
    }
}