package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Car extends Vehicle {

    private int secs0To60 = 4;

    private int power = 120;

    private float torque = 100;

    private int topSpeed = 50;

    public Car(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int secs0To60, int power, float torque, int topSpeed) {
        super(regNumber, model, cost, manufacturer, year);

    }

    public int getSecs0To60() {
        return secs0To60;
    }

    public int getPower() {
        return power;
    }

    public float getTorque() {
        return torque;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setSecs0To60(int secs0To60) {
        if(Utilities.validRange(secs0To60, 4, 25))
            this.secs0To60 = secs0To60;
    }

    public void setPower(int power) {
        if(Utilities.validRange(power, 120, 300))
            this.power = power;
    }

    public void setTorque(float torque) {
        if(Utilities.validRange(torque, 100, 400, 0))
            this.torque = torque;
    }

    public void setTopSpeed(int topSpeed) {
        if(Utilities.validRange(topSpeed, 50, 3000))
            this.topSpeed = topSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;
        return secs0To60 == car.secs0To60 && power == car.power && Float.compare(car.torque, torque) == 0 && topSpeed == car.topSpeed;
    }

    public abstract double getCarbonFootPrint();

    @Override
    public String toString() {
        return "Car{" +
                "secs0To60=" + secs0To60 +
                ", power=" + power +
                ", torque=" + torque +
                ", topSpeed=" + topSpeed +
                '}';
    }
}
