package models;

import utils.Utilities;

import java.util.Objects;

public class Scooter extends Vehicle{

    private int power = 250;

    private float weight = 5;

    private int topRiderWeight = 100;

    public Scooter(String regNumber, String model, float cost, Manufacturer manufacturer, int year, int power, float weight, int topRiderWeight) {
        super(regNumber, model, cost, manufacturer, year);
        setPower(power);
        setWeight(weight);
        setTopRiderWeight(topRiderWeight);
    }

    public int getPower() {
        return power;
    }

    public float getWeight() {
        return weight;
    }

    public int getTopRiderWeight() {
        return topRiderWeight;
    }

    public void setPower(int power) {
        if (Utilities.validRange(power, 250, 1000))
            this.power = power;
    }

    public void setWeight(float weight) {
        if (Utilities.validRange(weight, 5, 100, 0))
            this.weight = weight;
    }

    public void setTopRiderWeight(int topRiderWeight) {
        if (Utilities.validRange(topRiderWeight, 100, 120))
            this.topRiderWeight = topRiderWeight;
    }

    @Override
    public double getCarbonFootPrint() {
        return ((power * weight * getAge()) / 15000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter scooter)) return false;
        if (!super.equals(o)) return false;
        return power == scooter.power && Float.compare(scooter.weight, weight) == 0 && topRiderWeight == scooter.topRiderWeight;
    }

    public String toString() {
        return "Scooter{" + super.toString() +
                "power=" + power +
                ", Weight : " + weight +
                ", topRiderWeight=" + topRiderWeight +
                '}';
    }
}
