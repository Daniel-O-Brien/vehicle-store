package models;

import utils.Utilities;

public class ElectricCar extends Car{

    private int range = 100;

    private float engineKWatts = 40;

    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int secs0To60, int topSpeed, float torque, float engineKWats, int range) {
        super(regNumber, model, cost, manufacturer, year, secs0To60, power, torque, topSpeed);
        setRange(range);
        setEngineKWatts(engineKWats);
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if(Utilities.validRange(range,  40, 60))
            this.range = range;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if(Utilities.validRange(engineKWatts, 100, 500, 0))
            this.engineKWatts = engineKWatts;
    }

    public double getCarbonFootPrint() {
        return((engineKWatts * super.getAge()) / 20000);
    }
}
