package main;

import controllers.ManufacturerAPI;
import controllers.VehicleAPI;
import models.Manufacturer;
import utils.ScannerInput;

import java.io.File;

public class Driver {



        private VehicleAPI vehicleAPI;
        private ManufacturerAPI manufacturerAPI;

        public static void main(String[] args) throws Exception {
            new main.Driver().start();
        }

        public void start() {

//            vehicleAPI = new VehicleAPI(new File("vehicles.xml"));   //todo - write constructor for VehicleAPi
            manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));

            loadAllData();  //load all data once the serializers are set up
            runMainMenu();
        }

    private int mainMenu() {
        System.out.println("""
                         -------Vehicle Store-------------
                        |  1) Manufacturer CRUD MENU     |
                        |  2) Vehicle Store CRUD MENU    |
                        |  3) Reports MENU               |
                        |--------------------------------|
                        |  4) Search Manufacturers       |
                        |  5) Search Vehicles            |  
                        |  6) Sort Vehicles              | 
                        |--------------------------------|
                        |  10) Save all                  |
                        |  11) Load all                  |
                        |--------------------------------|
                        |  0) Exit                       |
                         --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

        private void runMainMenu() {
            int option = mainMenu();
            while (option != 0) {
                switch (option) {
                    case 1-> runManufacturerMenu();
                   // case 2 -> TODO run the Vehicle Store Menu and the associated methods (your design here)
                   // case 3 -> TODO run the Reports Menu and the associated methods (your design here)
                    //case 4 -> TODO run the search Manufacturers menu and associated methods (your design here)
                   // case 5 ->TODO run the search Vehicles menu and associated methods (your design here)
                    // case 6 ->TODO sorting menu and associated (your design here)
                    case 10 -> saveAllData();
                    case 11 -> loadAllData();
                    default ->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = mainMenu();
            }
            exitApp();
        }

        private void exitApp(){
            saveAllData();
            System.out.println("Exiting....");
            System.exit(0);
        }

        //----------------------
        //  Developer Menu Items
        //----------------------
        private int manufacturerMenu() {
            System.out.println("""
                --------Manufacturer Menu---------
               |  1) Add a manufacturer           |
               |  2) Delete a manufacturer        |
               |  3) Update manufacturer details  |
               |  4) List all manufacturers       |
               |  5) Find a manufacturer          |
               |  0) Return to main menu          |
                ----------------------------------""");
            return ScannerInput.readNextInt("==>>");
        }

        private void runManufacturerMenu() {
            int option = manufacturerMenu();
            while (option != 0) {
                switch (option) {
                    case 1 -> addManufacturer();
                    case 2 -> deleteManufacturer();
                    case 3 -> updateManufacturer();
                    case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                    case 5-> findManufacturer();
                    case 6-> listVehiclesByManufacturerName();
                    default->  System.out.println("Invalid option entered" + option);
                }
                ScannerInput.readNextLine("\n Press the enter key to continue");
                option = manufacturerMenu();
            }
        }

        private void addManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
            int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

            if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))){
                System.out.println("Add successful");
            }
            else{
                System.out.println("Add not successful");
            }
        }

        private void deleteManufacturer() {
            String manufacturerName = ScannerInput.readNextLine("Please enter the developer name: ");
            if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null){
                System.out.println("Delete successful");
            }
            else{
                System.out.println("Delete not successful");
            }
        }

        private void updateManufacturer(){
            Manufacturer manufacturer = getManufacturerByName();
            if (manufacturer != null){
                int numEmployees= ScannerInput.readNextInt("Please enter number of Employees: ");
                if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                    System.out.println("Number of Employees Updated");
                else
                    System.out.println("Number of Employees NOT Updated");
            }
            else
                System.out.println("Manufacturer name is NOT valid");
        }

        private void findManufacturer(){
            Manufacturer developer = getManufacturerByName();
            if (developer == null){
                System.out.println("No such manufacturer exists");
            }
            else{
                System.out.println(developer);
            }
        }

        private void listVehiclesByManufacturerName(){
            String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

            System.out.println(manufacturerAPI.listAllVehiclesByManufacturerName(manufacturer));
        }


        //---------------------
        //  App Store Menu
        //---------------------


        // public Vehicle(String regNumber, String  model, float cost, Manufacturer manufacturer, int  year) {
        private void addVehicle(){
            int vehicleType =  ScannerInput.readNextInt("""
        Which type of vehicle do you wish to add? 
        1) Carbon Fuel Car
        2) Electric Car
        3) Scooter """);

//            Manufacturer manufacturer = getManufacturerByName();
//            if (manufacturer != null){
//                String regNumber = ScannerInput.readNextLine("Please enter Reg number of new Vehicle: ");
//
//
//                if (vehicleAPI.isValidNewRegNumber(regNumber)){
//                    String  model = ScannerInput.readNextLine("\tmodel : ");
//                    float cost = ScannerInput.readNextFloat("\tcost : ");
//                    int  year = ScannerInput.readNextInt("\tYear of registration");
//                    switch (vehicleType) {
//                        case 1, 2 -> {
//                            int power = ScannerInput.readNextInt("\tpower : ");
//                            int    secs0To60 = ScannerInput.readNextInt("\ttime from 0 to 60 :  ");
//                            int  topSpeed = ScannerInput.readNextInt("\ttop speed : ");
//                            float torque = ScannerInput.readNextFloat("\tpower: ");
//                            switch (vehicleType) {
//                                case 1-> {
//                                    //TODO Carbon Fuel Car -
//                                   }
//                                case 2 -> {
//                                    //TODO Electric Car   -
//                                 }
//                            }
//                        }
//                        case 3 -> {
//                            //Scooter
//
//                            int power = ScannerInput.readNextInt("\tpower : ");
//                            float weight = ScannerInput.readNextFloat("\tweight : ");
//                            int topRiderWeight = ScannerInput.readNextInt("\ttop rider weight");
//          // todo - write addVehicle(.)                  vehicleAPI.addVehicle(new Scooter(regNumber, model, cost, manufacturer, year, power, weight,topRiderWeight));
//
//                        }
//                    }
//            }
//                else{
//                    System.out.println("Vehicle reg number  already exists.");
//                }
//            }
//
//            else{
//                System.out.println("Manufacturer name is NOT valid");
//            }
     }

        private void deleteApp(){
            //todo
        }



    private int vehicleReportsMenu() {
        System.out.println(""" 
                ---------- Vehicle Reports Menu  ---------------------
               | 1) List all vehicles                                 | 
               | 2) List all Electric Cars                            |
               | 3) List all Carbon Fuel Cars                         |
               | 4) List all Scooters                                 |
               | 5) List all Vehicles registered in a given year      |
               | 6) List all Vehicles registered after a given year   |
               | 7) List all carbon fuel by fuel type                 |
               | 8) List the top five carbon vehicles                 |
               | 0) Return to main menu                               | 
                 ----------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    private int manufacturerReportsMenu() {
        System.out.println(""" 
                ---------- Manufacturers Reports Menu  -------------
               | 1) List Manufacturers                              | 
               | 2) List Manufacturers from a given manufacturer    |
               | 3) List Manufacturers by a given name              |
               | 0) Return to main menu                             | 
                 ---------------------------------------------------  """);
        return ScannerInput.readNextInt("==>>");
    }
    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1-> System.out.println(manufacturerAPI.listManufacturers());
  //              case 2-> listAllVehiclesFromaGivenManufacturer();   todo write listAllVehiclesFromaGivenManufacturer
                case 3-> System.out.println("todo");
                default->  System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option =  manufacturerReportsMenu();
        }
    }


//    public void listAllVehiclesFromaGivenManufacturer() {
//        String manu  = ScannerInput.readNextLine("What manufacturer you want a list of cars for?  : ");
//        Manufacturer m = manufacturerAPI.getManufacturerByName(manu);
//        if (!(m == null))
//        System.out.println(vehicleAPI.listAllVehicleByChosenManufacturer(m));     todo write listAllVehicleByChosenManufacturer()
//        else
//            System.out.println("No manufacturer with tha name exists");
//    }


    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        // TODO try-catch to save the developers to XML file
        // TODO try-catch to save the apps in the store to XML file
    }

    private void loadAllData() {
        // TODO try-catch to load the developers from XML file
        // TODO try-catch to load the apps in the store from XML file
    }

//    private String getValidRegNumber(){
//            String vehicleRegNumber = ScannerInput.readNextLine("\tVehicle Reg Number (must be unique): ");
//            if (vehicleAPI.isValidNewRegNumber(vehicleRegNumber)) {
//                return vehicleRegNumber;
//            } else {
//                System.err.println("\tApp name already exists / is not valid.");
//                return "";
//            }
//        }

        private Manufacturer getManufacturerByName(){
            String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
            if (manufacturerAPI.isValidManufacturer(manufacturerName)){
                return manufacturerAPI.getManufacturerByName(manufacturerName);
            }
            else{
                return null;
            }
        }


    }

