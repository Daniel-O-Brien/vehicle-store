package models;

import utils.Utilities;

import java.util.Objects;

public abstract class Vehicle {
    private String model = "No model";

    private String regNumber = "No reg";

    private int year = 2000;

    private float cost = 1000;

    Manufacturer manufacturer;

    public Vehicle(String regNumber, String model, float cost, Manufacturer manufacturer, int year) {
        this.regNumber = Utilities.truncateString(regNumber, 8);
        this.model = Utilities.truncateString(model, 15);
        setCost(cost);
        setManufacturer(manufacturer);
        setYear(year);
    }

    public String getModel() {
        return model;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public int getYear() {
        return year;
    }

    public float getCost() {
        return cost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public int getAge() {
        return (2023 - getYear());
    }

    public void setModel(String model) {
        if(model.length() <= 15)
            this.model = model;
    }

    public void setRegNumber(String regNumber) {
        if(regNumber.length() <= 8)
            this.regNumber = regNumber;
    }

    public void setYear(int year) {
        if(Utilities.validRange(year, 2000, 2023))
            this.year = year;
    }

    public void setCost(float cost) {
        if(cost >= 1000)
            this.cost = cost;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract double getCarbonFootPrint();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return year == vehicle.year && Float.compare(vehicle.cost, cost) == 0 && Objects.equals(model, vehicle.model) && Objects.equals(regNumber, vehicle.regNumber) && Objects.equals(manufacturer, vehicle.manufacturer);
    }

    public String toString() {
        String age = "Brand New!";
        if(getAge() == 1)
            age = "1 year old";
        if(getAge() > 1)
            age = getAge() + " years old";
        return "Vehicle{" + "regNumber='" + regNumber + '\'' +", model='" + model + '\'' + ", cost=" + cost + ", manufacturer=" + manufacturer + year + " " + age + '}';
    }
}
