package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.CarbonFuelCar;
import models.ElectricCar;
import models.Manufacturer;
import models.Scooter;
import utils.Utilities;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import static utils.Utilities.tryParseFloat;
import static utils.Utilities.tryParseInt;

import java.io.File;
import java.io.FileWriter;
import java.io.ObjectOutputStream;


public class Driver {

        JOptionPane frame = new JOptionPane();

        private VehicleAPI vehicleAPI;
        private ManufacturerAPI manufacturerAPI;

        public static void main(String[] args) throws Exception {
            new main.Driver().start();
        }

        public void start() {

            vehicleAPI = new VehicleAPI(new File("vehicles.xml"));
            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
            loadAllData();
            runMainMenu();
        }

    private int mainMenu() {
        return tryParseInt(showInputDialog(frame, """
                        1) Manufacturer CRUD MENU
                        2) Vehicle Store CRUD MENU
                        3) Reports MENU
                        --------------------------------
                        4) Search Manufacturers
                        5) Search Vehicles
                        6) Sort Vehicles
                        --------------------------------
                        10) Save all
                        11) Load all
                        --------------------------------
                        0) Exit
                        --------------------------------""", "Vehicle Store", PLAIN_MESSAGE));
    }

        private void runMainMenu() {
            int option = mainMenu();
            while ((option != -1) && (option != 0)) {
                switch (option) {
                    case 1-> runManufacturerMenu();
                    case 2 -> runVehicleStoreMenu();
                    case 3 -> runReportsMenu();
                    case 4 -> runSearchManufacturersMenu();
                   // case 5 ->
                    case 10 -> saveAllData();
                    case 11 -> loadAllData();
                    default -> showMessageDialog(frame, "Invalid option entered: " + option, "Error", ERROR_MESSAGE);
                }
                option = mainMenu();
            }
            exitApp();
        }

        private void exitApp(){
            int option = showConfirmDialog(frame, "Are you sure you want to exit?", "Exit App", YES_NO_OPTION, QUESTION_MESSAGE);
            if(option == 0) {
                if (showConfirmDialog(frame, "Do you want to save before exiting?", "Exit App", YES_NO_OPTION, QUESTION_MESSAGE) == 0)
                    saveAllData();
                System.exit(0);
            }
            else
                runMainMenu();
        }

        //----------------------
        //  Developer Menu Items
        //----------------------
        private int manufacturerMenu() {
            return tryParseInt(showInputDialog(frame, """
               1) Add a manufacturer
               2) Delete a manufacturer
               3) Update manufacturer details
               4) List all manufacturers
               5) Find a manufacturer
               6) List vehicles by manufacturer name
               0) Return to main menu
               ----------------------------------""", "Manufacturer Menu", PLAIN_MESSAGE));
        }

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while ((option != -1) && (option != 0)) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> JOptionPane.showMessageDialog(frame, manufacturerAPI.listManufacturers());
                    case 5 -> findManufacturer();
                    case 6 -> listVehiclesByManufacturerName();
                    default->  JOptionPane.showMessageDialog(frame, "Invalid option entered: " + option, "Error", ERROR_MESSAGE);
                }
                option = manufacturerMenu();
            }
        }

        private void addManufacturer() {
            String manufacturerName = showInputDialog(frame, "Please enter the manufacturer name: ", "Add Manufacturer", QUESTION_MESSAGE);
            int manufacturerNumEmployees = Integer.parseInt(showInputDialog(frame, "Please enter the number of employees: ", "Add Manufacturer", QUESTION_MESSAGE));

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                showMessageDialog(frame, "Add successful", "Add Manufacturer", INFORMATION_MESSAGE);
            }
            else{
                showMessageDialog(frame, "Add not successful", "Add Manufacturer", ERROR_MESSAGE);
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = showInputDialog(frame, "Please enter the manufacturer name: ", "Delete Manufacturer", QUESTION_MESSAGE);
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                showMessageDialog(frame, "Delete successful", "Delete Manufacturer", INFORMATION_MESSAGE);
            }
            else{
                showMessageDialog(frame, "Delete not successful", "Delete Manufacturer", ERROR_MESSAGE);
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= tryParseInt(showInputDialog(frame, "Please enter number of Employees: ", "Update Manufacturer", QUESTION_MESSAGE));
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    showMessageDialog(frame, "Number of Employees Updated", "Update Manufacturer", INFORMATION_MESSAGE);
                else
                    showMessageDialog(frame, "Number of Employees NOT Updated", "Update Manufacturer", ERROR_MESSAGE);
            }
            else
                showMessageDialog(frame, "Manufacturer name is NOT valid");
        }

        private void findManufacturer() {
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null)
                showMessageDialog(frame, manufacturer);
            else
                showMessageDialog(frame, "No such manufacturer exists", "Find Manufacturer", ERROR_MESSAGE);
        }

        private void listVehiclesByManufacturerName(){
            String manufacturer = showInputDialog(frame, "Enter the manufacturer's name:", "List Vehicles By Manufacturer Name", QUESTION_MESSAGE);
            showMessageDialog(frame, vehicleAPI.listAllVehicleByChosenManufacturer(manufacturerAPI.getManufacturerByName(manufacturer)));
        }


        //---------------------
        //  App Store Menu
        //---------------------

    private int vehicleStoreMenu() {
        return tryParseInt(showInputDialog(frame, """
                1) Add a vehicle
                2) Delete a vehicle
                3) List all vehicles
                4) Update vehicle
                0) Return to main menu """, "Vehicle Store Menu", PLAIN_MESSAGE));

    }

        private void runVehicleStoreMenu() {
                int option = vehicleStoreMenu();
                while ((option != -1) && (option != 0)) {
                    switch (option) {
                        case 1 -> addVehicle();
                        case 2 -> deleteVehicle();
                        case 3 -> JOptionPane.showMessageDialog(frame, vehicleAPI.listAllVehicles(), "List Vehicles", PLAIN_MESSAGE);
                        case 4 -> updateVehicle();
                        default -> showInputDialog("Invalid option entered: " + option);
                    }
                    option = vehicleStoreMenu();
                }
        }


        // public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
        private void addVehicle() {
            int vehicleType = tryParseInt(showInputDialog(frame, """
        Which type of vehicle do you wish to add? 
        1) Carbon Fuel Car
        2) Electric Car
        3) Scooter""", "Add Vehicle", PLAIN_MESSAGE));


            Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(showInputDialog(frame, "Enter Manufacturer", "Add Vehicle", QUESTION_MESSAGE));
            if (manufacturer != null){
                String regNumber = showInputDialog(frame, "Please enter Reg number of new Vehicle: ", "Add Vehicle", QUESTION_MESSAGE);

                if (vehicleAPI.isValidNewRegNumber(regNumber)){
                    String model = showInputDialog(frame, "model: ", "Add Vehicle", QUESTION_MESSAGE);
                    float cost = tryParseFloat(showInputDialog(frame, "cost : ", "Add Vehicle", QUESTION_MESSAGE));
                    int year = tryParseInt(showInputDialog(frame, "Year of registration", "Add Vehicle", QUESTION_MESSAGE));
                    switch (vehicleType) {
                        case 1, 2 -> {
                            int power = tryParseInt(showInputDialog(frame, "power :", "Add Vehicle", QUESTION_MESSAGE));
                            int secs0To60 = tryParseInt(showInputDialog(frame, "time from 0 to 60 :", "Add Vehicle", QUESTION_MESSAGE));
                            int  topSpeed = tryParseInt(showInputDialog(frame, "top speed :", "Add Vehicle", QUESTION_MESSAGE));
                            float torque = tryParseFloat(showInputDialog(frame, "power:", "Add Vehicle", QUESTION_MESSAGE));
                            switch (vehicleType) {
                                case 1-> {
                                    float fuelConsumption = tryParseFloat(showInputDialog(frame, "Fuel Consumption:", "Add Vehicle", QUESTION_MESSAGE));
                                    float carbonEmission = tryParseFloat(showInputDialog(frame, "Carbon Emission", "Add Vehicle", QUESTION_MESSAGE));
                                    boolean automatic = Utilities.numToBoolean(showOptionDialog(frame, "Is the car an Automatic (y/n", "Add Vehicle", YES_NO_OPTION, QUESTION_MESSAGE, null, null, null));
                                    String fuelType = showInputDialog(frame, "Fuel Type", "Add Vehicle", QUESTION_MESSAGE);
                                    int engineSize = tryParseInt(showInputDialog(frame, "Engine size:", "Add Vehicle", QUESTION_MESSAGE));
                                    if(vehicleAPI.addVehicle(new CarbonFuelCar(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque, fuelType, fuelConsumption, carbonEmission, engineSize, automatic)))
                                        showMessageDialog(frame, "Carbon Emission Car Added Successfully", "Add Vehicle", INFORMATION_MESSAGE);
                                    else
                                        showMessageDialog(frame, "Carbon Emission Car Not Added", "Add Vehicle", WARNING_MESSAGE);
                                   }
                                case 2 -> {
                                    int range = tryParseInt(showInputDialog(frame, "Range:", "Add Vehicle", QUESTION_MESSAGE));
                                    float engineKWats = tryParseFloat(showInputDialog(frame, "Engine KWatts:", "Add Vehicle", QUESTION_MESSAGE));
                                    if(vehicleAPI.addVehicle(new ElectricCar(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque, engineKWats, range)))
                                        showMessageDialog(frame, "Electric Car Added Successfully", "Add Vehicle", INFORMATION_MESSAGE);
                                    else
                                        showMessageDialog(frame, "Electric Car Not Added", "Add Vehicle", ERROR_MESSAGE);
                                }
                            }
                        }
                        case 3 -> {
                            int power = tryParseInt(showInputDialog(frame, "power :", "Add Vehicle", QUESTION_MESSAGE));
                            float weight = tryParseFloat(showInputDialog(frame, "weight : ", "Add Vehicle", QUESTION_MESSAGE));
                            int topRiderWeight = tryParseInt(showInputDialog(frame, "top rider weight:", "Add Vehicle", QUESTION_MESSAGE));
                            if(vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight, topRiderWeight)))
                                showMessageDialog(frame, "Scooter Added Successfully", "Add Vehicle", INFORMATION_MESSAGE);
                            else
                                showMessageDialog(frame, "Scooter Not Added", "Add Vehicle", ERROR_MESSAGE);
                        }
                    }
            }
                else{
                    showMessageDialog(frame, "Vehicle reg number already exists.", "Add Vehicle", ERROR_MESSAGE);
                }
            }

            else{
                showMessageDialog(frame, "Manufacturer name is NOT valid", "Add Vehicle", ERROR_MESSAGE);
            }
     }

        private void deleteVehicle(){
            String regNumber = showInputDialog(frame, "Please enter the registration number: ", "Delete Vehicle", QUESTION_MESSAGE);
            if (vehicleAPI.deleteVehicleByRegNumber(regNumber) != null){
                showMessageDialog(frame, "Delete successful", "Delete Vehicle", INFORMATION_MESSAGE);
            }
            else{
                showMessageDialog(frame, "Delete not successful", "Delete Vehicle", ERROR_MESSAGE);
            }
        }

        private void updateVehicle() {
            int vehicleType = tryParseInt(showInputDialog(frame, """
                    Which type of vehicle do you wish to update? 
                    1) Carbon Fuel Car
                    2) Electric Car
                    3) Scooter""", "Add Vehicle", PLAIN_MESSAGE));
            if (vehicleType >= 0 && vehicleType <= 3) {
                String regNumber = showInputDialog(frame, "Please enter Reg number of Vehicle to Update: ", "Update Vehicle", QUESTION_MESSAGE);
                if (vehicleAPI.getVehicleByRegNumber(regNumber) != null) {
                    Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(showInputDialog(frame, "Please enter Manufacturer", "Update Vehicle", QUESTION_MESSAGE));
                    String model = showInputDialog(frame, "model : ");
                    float cost = tryParseFloat(showInputDialog(frame, "cost : ", "Update Vehicle", QUESTION_MESSAGE));
                    int year = tryParseInt(showInputDialog(frame, "Year of registration", "Update Vehicle", QUESTION_MESSAGE));
                    switch (vehicleType) {
                        case 1, 2 -> {
                            int power = tryParseInt(showInputDialog(frame, "power :", "Update Vehicle", QUESTION_MESSAGE));
                            int secs0To60 = tryParseInt(showInputDialog(frame, "time from 0 to 60 :", "Update Vehicle", QUESTION_MESSAGE));
                            int topSpeed = tryParseInt(showInputDialog(frame, "top speed :", "Update Vehicle", QUESTION_MESSAGE));
                            float torque = tryParseFloat(showInputDialog(frame, "torque:", "Update Vehicle", QUESTION_MESSAGE));
                            switch (vehicleType) {
                                case 1 -> {
                                    float fuelConsumption = tryParseFloat(showInputDialog(frame, "Fuel Consumption:", "Update Vehicle", QUESTION_MESSAGE));
                                    float carbonEmission = tryParseFloat(showInputDialog(frame, "Carbon Emission", "Update Vehicle", QUESTION_MESSAGE));
                                    boolean automatic = Utilities.numToBoolean(showOptionDialog(frame, "Is the car an Automatic (y/n", "Update Vehicle", YES_NO_OPTION, QUESTION_MESSAGE, null, null, null));
                                    String fuelType = showInputDialog(frame, "Fuel Type", "Update Vehicle", QUESTION_MESSAGE);
                                    int engineSize = tryParseInt(showInputDialog(frame, "Engine size:", "Update Vehicle", QUESTION_MESSAGE));
                                    if (vehicleAPI.updateCarbonFuelCar(regNumber, new CarbonFuelCar(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque, fuelType, fuelConsumption, carbonEmission, engineSize, automatic)))
                                        showMessageDialog(frame, "Carbon Emission Car Updated Successfully", "Update Vehicle", INFORMATION_MESSAGE);
                                    else
                                        showMessageDialog(frame, "Carbon Emission Car Not Updated", "Update Vehicle", WARNING_MESSAGE);
                                }
                                case 2 -> {
                                    int range = tryParseInt(showInputDialog(frame, "Range:", "Update Vehicle", QUESTION_MESSAGE));
                                    float engineKWats = tryParseFloat(showInputDialog(frame, "Engine KWatts:", "Update Vehicle", QUESTION_MESSAGE));
                                    if (vehicleAPI.updateElectricCar(regNumber, new ElectricCar(regNumber, model, cost, manufacturer, year, power, secs0To60, topSpeed, torque, engineKWats, range)))
                                        showMessageDialog(frame, "Electric Car Updated Successfully", "Update Vehicle", INFORMATION_MESSAGE);
                                    else
                                        showMessageDialog(frame, "Electric Car Not Updated", "Update Vehicle", ERROR_MESSAGE);
                                }
                            }
                        }
                        case 3 -> {
                            int power = tryParseInt(showInputDialog(frame, "power :", "Update Vehicle", QUESTION_MESSAGE));
                            float weight = tryParseFloat(showInputDialog(frame, "weight : ", "Update Vehicle", QUESTION_MESSAGE));
                            int topRiderWeight = tryParseInt(showInputDialog(frame, "top rider weight:", "Update Vehicle", QUESTION_MESSAGE));
                            if (vehicleAPI.updateScooter(regNumber, new Scooter(regNumber, model, cost, manufacturer, year, power, weight, topRiderWeight)))
                                showMessageDialog(frame, "Scooter Updated Successfully", "Update Vehicle", INFORMATION_MESSAGE);
                            else
                                showMessageDialog(frame, "Scooter Not Updated", "Update Vehicle", ERROR_MESSAGE);
                        }
                    }
                }
                else
                    showMessageDialog(frame, "Not valid registration number", "Update Vehicle", ERROR_MESSAGE);
            }
            else
                showMessageDialog(frame, "Not valid option", "Update Vehicle", ERROR_MESSAGE);
        }


    private int reportsMenu() {
        return tryParseInt(showInputDialog(frame,"""
               | 1) Manufacturers Overview  | 
               | 2) Vehicles Overview       |
               | 0) Return to main menu     |""", "Reports Menu", PLAIN_MESSAGE));
    }


    private void runReportsMenu() {
        int option = reportsMenu();
        while((option != -1) && (option != 0)) {
            switch(option) {
                case 1 -> runManufacturerReports();
                case 2 -> runVehicleStoreMenu();
                default -> showMessageDialog(frame, "Invalid option entered" + option, "Reports Menu", ERROR_MESSAGE);
            }
        }
    }

    private int vehicleReportsMenu() {
        return tryParseInt(showInputDialog(frame,"""
               | 1) List all vehicles                                 | 
               | 2) List all Electric Cars                            |
               | 3) List all Carbon Fuel Cars                         |
               | 4) List all Scooters                                 |
               | 5) List all Vehicles registered in a given year      |
               | 6) List all Vehicles registered after a given year   |
               | 7) List all carbon fuel by fuel type                 |
               | 8) List the top five carbon vehicles                 |
               | 0) Return to main menu                               |""", "Vehicle Reports Menu", PLAIN_MESSAGE));
    }
    private int manufacturerReportsMenu() {
        return tryParseInt(showInputDialog(frame, """
               | 1) List Manufacturers
               | 2) List Manufacturers from a given manufacturer
               | 0) Return to main menu
                 ---------------------------------------------------  """, "Manufacturer's Reports Menu", PLAIN_MESSAGE));
    }


    private void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while ((option != -1) && (option != 0)) {
            switch (option) {
                case 1-> showMessageDialog(frame, manufacturerAPI.listManufacturers());
                case 2-> listAllVehiclesFromAGivenManufacturer();
                default->  showMessageDialog(frame, "Invalid option entered" + option, "Manufacturer Report", ERROR_MESSAGE);
            }
            option =  manufacturerReportsMenu();
        }
    }


    private void listAllVehiclesFromAGivenManufacturer() {
        String manu  = showInputDialog(frame, "What manufacturer you want a list of cars for?  : ", "Manufacturer Report", QUESTION_MESSAGE);
        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
        if (m != null)
            showMessageDialog(frame, vehicleAPI.listAllVehicleByChosenManufacturer(m), "Manufacturer Report", INFORMATION_MESSAGE);
        else
            showMessageDialog(frame, "No manufacturer with tha name exists", "Manufacturer Report", ERROR_MESSAGE);
    }



    private String getValidRegNumber(){
            String vehicleRegNumber = showInputDialog(frame, "Vehicle Reg Number (must be unique): ", "Manufacturer Report", QUESTION_MESSAGE);
            if (vehicleAPI.isValidNewRegNumber(vehicleRegNumber)) {
                return vehicleRegNumber;
            } else {
                showMessageDialog(frame, "App name already exists / is not valid.", "Error", ERROR_MESSAGE);
                return "";
            }
        }

    private Manufacturer getManufacturerByName(){
        String manufacturerName = showInputDialog(frame, "Please enter the manufacturer's name: ", "Get Manufacturer By Name", QUESTION_MESSAGE);
        if (manufacturerAPI.isValidManufacturer(manufacturerName)) {
            return manufacturerAPI.getManufacturerByName(manufacturerName);
        }
        else{
            return null;
        }
    }

    private int searchManufacturersMenu() {
            return tryParseInt(showInputDialog(frame, """
                    1) List manufacturers
                    """, "Search Manufacturer", PLAIN_MESSAGE));
    }

    private void runSearchManufacturersMenu() {
        int option = searchManufacturersMenu();
        while ((option != -1) && (option != 0)) {
            switch (option) {
                case 1 -> showMessageDialog(frame, manufacturerAPI.listManufacturers(), "Search Manufacturers", INFORMATION_MESSAGE);
                default->  showMessageDialog(frame, "Invalid option entered" + option, "Manufacturer Report", ERROR_MESSAGE);
            }
            option =  manufacturerReportsMenu();
        }
    }

    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
            try{
                vehicleAPI.save();
                manufacturerAPI.save();
                showMessageDialog(frame, "Save Successful", "Save", INFORMATION_MESSAGE);
            }
            catch(Exception e) {
                showMessageDialog(frame, "Error saving", "Save", ERROR_MESSAGE);
            }
        }

    private void loadAllData() {
        try {
            vehicleAPI.load();
            manufacturerAPI.load();
            showMessageDialog(frame, "Loading Successful", "Load", INFORMATION_MESSAGE);
        }
        catch(Exception e) {
            showMessageDialog(frame, "Error Loading", "Load", ERROR_MESSAGE);
        }
    }
}

