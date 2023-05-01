package controllers;

import models.*;
import utils.FuelTypeUtility;
import utils.Serializer;
import utils.Utilities;

import java.io.*;
import java.util.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class VehicleAPI implements Serializer{

    private List<Vehicle> vehicles = new ArrayList<>();

    private File file;

    public VehicleAPI(File file) {
        this.file = file;
    }

    public void sortByCarbonFootprintDescending() {
    }

    /**
     * Lists All Carbon Fuel Cars by its fuel type
     * @param fuelType either diesel or petrol
     * @return a String of all carbon fuel cars with that fuel type
     *
     */
    public String listAllCarbonFuelsByFuelType(String fuelType) {
       if(FuelTypeUtility.validFuelType(fuelType)) {
          String str = "";
          for(Vehicle vehicle : vehicles) {
              if(((CarbonFuelCar) vehicle).getFuelType() == fuelType)
                  str += vehicle;
              if(str != "")
                  return str;
              return "No vehicles with " + fuelType + " exists";
          }
       }
       return "Invalid fuel type entered";
    }

    /**
     * Lists All vehicles after a certain year
     * @param year the year to list vehicles after
     * @return a String of all vehicles after given year
     *
     */
    public String listAllVehiclesAfterAGivenYear(int year) {
        String str = "";
        for(Vehicle vehicle : vehicles)
            if(vehicle.getYear() >= year)
                str += vehicles.indexOf(vehicle) + " " + vehicle;
        if(str != "")
            return str;
        return "No vehicles exist later than " + year;
    }

    /**
     * gets a vehicle by its registration number
     * @param regNumber the registration number of vehicle
     * @return an object of type Vehicle which is the vehicle
     *
     */

    public Vehicle getVehicleByRegNumber(String regNumber) {
        for(Vehicle vehicle: vehicles)
            if (vehicle.getRegNumber().equals(regNumber))
                return vehicle;
        return null;
    }

    /**
     * Lists all vehicles
     * @return a String of all vehicles
     *
     */
    public String listAllVehicles() {
        if(!vehicles.isEmpty()) {
            String str = "";
            for (Vehicle vehicle : vehicles)
                str += vehicles.indexOf(vehicle) + ": " + vehicle + "\n";
            return str;
        }
        return "No Vehicles";

    }

    /**
     * Updates an electric car
     * @param regNumber the registration number of an electirc car
     * @param electricCar object of the updated electric car
     * @return true if updated
     *
     */

    public boolean updateElectricCar(String regNumber, ElectricCar electricCar) {
        Vehicle vehicle = getVehicleByRegNumber(regNumber);
        if(vehicle != null && vehicle instanceof ElectricCar) {
            vehicles.set(vehicles.indexOf(vehicle), electricCar);
            return true;
        }
        return false;
    }

    /**
     * sorts the arraylist by cost descending
     *
     */

    public void sortByCostDescending() {
        for (int i = vehicles.size() -1; i >= 0; i--)
        {
            int lowestIndex = 0;
            for (int j = 1; j < i; j++)
            {
                if (vehicles.get(j).getCost() <= vehicles.get(lowestIndex).getCost()) {
                    lowestIndex = j;
                }
            }
            swapProducts(vehicles, i, lowestIndex);
        }
    }

    /**
     * Updates a carbon fuel car
     * @param regNumber registration n8umber of carbin fuel car to update
     * @param carbonFuelCar object of updated carbon fuel car
     * @return true if updated
     *
     */

    public boolean updateCarbonFuelCar(String regNumber, CarbonFuelCar carbonFuelCar) {
        Vehicle vehicle = getVehicleByRegNumber(regNumber);
        if(vehicle != null && vehicle instanceof CarbonFuelCar) {
            vehicles.set(vehicles.indexOf(vehicle), carbonFuelCar);
            return true;
        }
        return false;
    }

    /**
     * Updates a  scooter
     * @param regNumber registration n8umber of carbin fuel car to update
     * @param scooter object of updated carbon fuel car
     * @return true if updated
     *
     */

    public boolean updateScooter(String regNumber, Scooter scooter) {
        Vehicle vehicle = getVehicleByRegNumber(regNumber);
        if(vehicle != null && vehicle instanceof Scooter) {
            vehicles.set(vehicles.indexOf(vehicle), scooter);
            return true;
        }
        return false;
    }

    /**
     * Gets the number of vehicles by a chosen manufacturer
     * @param manufacturer the manufacturer to get
     * @return int of number of vehicles
     *
     */
    public int numberOfVehiclesByChosenManufacturer(Manufacturer manufacturer) {
        int num = 0;
        for(Vehicle vehicle : vehicles)
            if(vehicle.getManufacturer() == manufacturer)
                num++;
        return num;
    }

    /**
     * Gets the number of scooters
     * @return int of number of scooters
     *
     */

    public int numberOfScooters() {
        int num = 0;
        for(Vehicle vehicle : vehicles)
            if(vehicle instanceof Scooter)
                num++;
        return num;
    }
    /**
     * Lists all vehicles equal to a given year
     * @param year the year to get vehicles for
     * @return String of all vehicles
     *
     */
    public String listAllVehiclesEqualToAGivenYear(int year) {
        String str = "";
        for(Vehicle vehicle : vehicles)
            if(vehicle.getYear() == year)
                str += vehicle;
        if(str != "")
            return str;
        return "No vehicles exist for " + year;
    }

    /**
     * Gets the number of vehicles by a chosen manufacturer
     * @param manufacturer the manufacturer to get
     * @return int of number of vehicles
     *
     */
    public void sortByCarbonFootprintAscending() {
        for (int i = vehicles.size() -1; i >= 0; i--)
        {
            int lowestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (vehicles.get(j).getCarbonFootPrint() > vehicles.get(lowestIndex).getCarbonFootPrint()) {
                    lowestIndex = j;
                }
            }
            swapProducts(vehicles, i, lowestIndex);
        }
    }

    public String listAllElectricCars() {
        if(!vehicles.isEmpty()) {
            String str = "";
            for(Vehicle electricCar : vehicles)
                if(electricCar instanceof ElectricCar)
                    str += electricCar;
            return str;
        }
        return "No Electric Cars";
    }

    public String listAllScooters() {
        if(!vehicles.isEmpty()) {
            String str = "";
            for(Vehicle scooter : vehicles)
                if(scooter instanceof Scooter)
                    str += scooter;
            return str;
        }
        return "No Scooters";
    }

    public String listAllCarbonFuelCars() {
        if(!vehicles.isEmpty()) {
            String str = "";
            for(Vehicle carbonFuelCar : vehicles)
                if(carbonFuelCar instanceof CarbonFuelCar)
                    str += carbonFuelCar;
            return str;
        }
        return "No Carbon Fuel Cars";
    }

    public Vehicle getVehicleByIndex(int index) {
        if(Utilities.isValidIndex(vehicles, index))
            return vehicles.get(index);
        return null;
    }

    public int numberOfCarbonCars() {
        int num = 0;
        for(Vehicle vehicle : vehicles)
            if(vehicle instanceof CarbonFuelCar)
                num++;
        return num;
    }

    public List<Vehicle> topFiveCarbonVehicles() {
        return null;
    }

    public Vehicle deleteVehicleByRegNumber(String regNumber) {
        for(Vehicle vehicle: vehicles)
            if (vehicle.getRegNumber().equals(regNumber)) {
                Vehicle vehicleRemoved = vehicle;
                vehicles.remove(vehicle);
                return vehicleRemoved;
            }
        return null;
    }

    public int numberOfVehicles() {
        return vehicles.size();
    }

    public int numberOfElectricCars() {
        int num = 0;
        for(Vehicle vehicle : vehicles)
            if(vehicle instanceof ElectricCar)
                num++;
        return num;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if(vehicle.getRegNumber() != null)
            return vehicles.add(vehicle);
        return false;
    }

    public void sortByAgeAscending() {
        for (int i = vehicles.size() -1; i >= 0; i--)
        {
            int lowestIndex = 0;
            for (int j = 0; j <= i; j++)
            {
                if (vehicles.get(j).getAge() > vehicles.get(lowestIndex).getCost()) {
                    lowestIndex = j;
                }
            }
            swapProducts(vehicles, i, lowestIndex);
        }
    }

    public String listAllVehicleByChosenManufacturer(Manufacturer manufacturer) {
        if(!vehicles.isEmpty()) {
            String str = "";
            for (Vehicle vehicle : vehicles)
                if (vehicle.getManufacturer() == manufacturer)
                    str += vehicle;
            if(str != "")
                return str;
            return "There are no vehicles who have " + manufacturer + " in the list";
        }
        return "No Vehicles added";
    }

    public Vehicle deleteVehicleByIndex(int index) {
        if(Utilities.isValidIndex(vehicles, index))
            return vehicles.remove(index);
        return null;
    }

    // checks if regNumber is a new reg number i.e. it does not already exist in the collection
    public boolean isValidNewRegNumber(String regNumber){
        for(Vehicle vehicle: vehicles)
            if (vehicle.getRegNumber().equals(regNumber))
                return false;
        return true;
    }

    private void swapProducts(List<Vehicle> vehicles, int i, int j) {
        Vehicle smaller = vehicles.get(i);
        Vehicle bigger = vehicles.get(j);

        vehicles.set(i,bigger);
        vehicles.set(j,smaller);
    }

    //---------------------
    // Persistence methods
    //---------------------
    /**
     * The load method uses the XStream component to read all the objects from the xml
     * file stored on the hard disk.  The read objects are loaded into the associated ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Vehicle.class, Car.class, CarbonFuelCar.class,
                                            ElectricCar.class, Scooter.class, Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        vehicles = (List<Vehicle>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the ArrayList
     * to the xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(vehicles);
        out.close();
    }

    public String fileName(){
        return this.file.toString();
    }


}
