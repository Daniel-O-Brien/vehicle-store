package models;

import utils.Utilities;

import java.util.Objects;

public class ElectricCar extends Car{

    private int range = 100;

    private float engineKWatts = 40;

    public ElectricCar(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, int secs0To60, int topSpeed, float torque, float engineKWats, int range) {
        super(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque);
        setRange(range);
        setEngineKWatts(engineKWats);
    }

 public int getRange() {
        return range;
    }

    public void setRange(int range) {
        if(Utilities.validRange(range,  100, 500))
            this.range = range;
    }

    public float getEngineKWatts() {
        return engineKWatts;
    }

    public void setEngineKWatts(float engineKWatts) {
        if(Utilities.validRange(engineKWatts, 40, 60, 0))
            this.engineKWatts = engineKWatts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectricCar that = (ElectricCar) o;
        return getRange() == that.getRange() && Float.compare(that.getEngineKWatts(), getEngineKWatts()) == 0;
    }

    public double getCarbonFootPrint() {
        return((engineKWatts * super.getAge()) / 20000);
    }
}
