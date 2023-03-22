package controllers;

import models.Manufacturer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleAPITest {

    private Scooter scooterBelowBoundary, scooterOnBoundary, scooterAboveBoundary, scooterInvalidData;
    private ElectricCar electricCarBelowBoundary, electricCarOnBoundary, electricCarAboveBoundary, electricCarInvalidData;
    private CarbonFuelCar carbonFuelBelowBoundary, carbonFuelOnBoundary, carbonFuelAboveBoundary, carbonFuelInvalidData;

    private Manufacturer ford = new Manufacturer("Ford", 1020);
    private Manufacturer kia = new Manufacturer("Kia", 1200);
    private Manufacturer audi = new Manufacturer("Audi", 1325);
    private Manufacturer tesla = new Manufacturer("Tesla", 3245);
    private Manufacturer mazda = new Manufacturer("Mazda", 2377);
    private Manufacturer hyundai = new Manufacturer("Hyundai", 2765);

   private VehicleAPI populatedVehicles = new VehicleAPI(new File("vehicles.xml"));
   private VehicleAPI emptyVehicles = new VehicleAPI(new File("vehiclesempty.xml"));

    @BeforeEach
    void setUp() {
        // Vehicle Validation Rules:
        //    regNumber (max 8 chars, default ""), model (max 15 chars, default ""),
        //    cost (>= 1000, default 1000), year (2000 - 2023, default 2000)

        // Scooter Validation Rules:
        //    power (250 -> 1000, default 250), weight (5 -> 100, default 5), topRiderWeight (100 -> 120, default 100)
        scooterBelowBoundary = new Scooter("SCOOT12", "Scootn1 Master", 999,
                ford, 1999, 119, 4, 99);

        scooterOnBoundary = new Scooter("SCOOT321", "MasterScooter-3", 1000,
                kia, 2000, 120, 5, 100);

        scooterAboveBoundary = new Scooter("SC 123456", "Speed Scooter X1", 1001,
                tesla, 2001, 121, 6, 101);

        scooterInvalidData = new Scooter(null, null, -1,
                null, -1, -1, -1, -1);


        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0),
        electricCarBelowBoundary = new ElectricCar("Elec987","Electric 12111", 999, mazda, 2020, 120, 5, 100, 100, 50, 100);

       electricCarOnBoundary = new ElectricCar("Elec5678", "Electric 123456", 1000, kia, 2000, 120, 4, 50, 100, 40, 100);

        electricCarAboveBoundary = new ElectricCar("Elec12345", "Electric 1234567", 1001,
                tesla, 2001, 121, 5, 51, 101, 41, 101);

        electricCarInvalidData = new ElectricCar(null, null, -1,
                null, -1, -1, -1, -1, -1, -1, -1);


        // Carbon Fuel Car Validation Rules:
        //    fuelType (diesel or petrol, default petrol), fuelConsumption (5 -> 20, default 5),
        //    carbonEmission (>0, default 0.1), engineSize (800->2500, default 800)
        carbonFuelBelowBoundary = new CarbonFuelCar("Car1234", "CarbonCar 1234", 999,
                ford, 1999, 119, 3, 49, 99, "diesel",
                4, 0, 799, false);

        carbonFuelOnBoundary = new CarbonFuelCar("Car54321", "CarbonCar 12345", 1000,
                kia, 2000, 120, 4, 50, 100, "diesel",
                5, 1, 800, false);

        carbonFuelAboveBoundary = new CarbonFuelCar("Car345678", "Carbon Car 12345", 1001,
                tesla, 2001, 121, 5, 51, 101, "petrol",
                6, 2, 801, false);

        carbonFuelInvalidData = new CarbonFuelCar(null, null, -1,
                null, -1, -1, -1, -1, -1, null,
                -1, -1, -1, false);

        //not included - scooterOnBoundary, scooterInvalidData, electricCarAboveBoundary,
        //               carbonFuelBelowBoundary, carbonFuelInvalidData.
        populatedVehicles.addVehicle(scooterBelowBoundary);     //SCOOT12
        populatedVehicles.addVehicle(electricCarOnBoundary);    //
        populatedVehicles.addVehicle(carbonFuelAboveBoundary);  //Car34567
        populatedVehicles.addVehicle(electricCarBelowBoundary); //MAZ123
        populatedVehicles.addVehicle(scooterAboveBoundary);     //SC 12345
        populatedVehicles.addVehicle(electricCarInvalidData);   //not added as the reg is null
        populatedVehicles.addVehicle(carbonFuelOnBoundary);     //Car54321
    }

    @AfterEach
    void tearDown() {
        scooterBelowBoundary = scooterOnBoundary = scooterAboveBoundary = scooterInvalidData = null;
        carbonFuelBelowBoundary = carbonFuelOnBoundary = carbonFuelAboveBoundary = carbonFuelInvalidData = null;
        electricCarBelowBoundary = electricCarOnBoundary = electricCarAboveBoundary = electricCarInvalidData = null;
        mazda = audi = tesla = ford = hyundai = null;
        populatedVehicles = emptyVehicles = null;
    }

    @Nested
    class GettersAndSetters {

    }

    @Nested
    class CRUDMethods {

    }

    @Nested
    class ListingMethods {

        @Test
        void listAllReturnsNoVehiclesStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyVehicles.numberOfVehicles());
            assertTrue(emptyVehicles.listAllVehicles().toLowerCase().contains("no vehicles"));
            System.out.println(populatedVehicles.listAllVehicles());
        }

        @Test
        void listAllReturnsVehiclesStoredWhenArrayListHasVehiclesStored() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            String vehicles = populatedVehicles.listAllVehicles();
            //checks for objects in the string
            assertTrue(vehicles.contains("SC 12345"));
            assertTrue(vehicles.contains("SCOOT12"));
            assertTrue(vehicles.contains("Elec987"));
            assertTrue(vehicles.contains("Elec5678"));
            assertTrue(vehicles.contains("Car54321"));
            assertTrue(vehicles.contains("Car34567"));
        }

        @Test
        void listBySelectedYearReturnsNoVehiclesWhenNoneExistForEnteredYear() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2003);
            assertTrue(vehicles.contains("No vehicles"));
        }

        @Test
        void listBySelectedYearReturnsVehiclesWhenVehiclesExistForEnteredYear() {
            System.out.println(populatedVehicles.listAllVehicles());
            assertEquals(6, populatedVehicles.numberOfVehicles());

            String vehicles = populatedVehicles.listAllVehiclesEqualToAGivenYear(2001);

            //checks for the objects in the string
            assertTrue(vehicles.contains("Car34567"));
            assertTrue(vehicles.contains("SC 12345"));
        }
    }

    @Nested
    class ReportingMethods {

    }

    @Nested
    class SearchingMethods {

    }

    @Nested
    class SortingMethods {

        @Test
        void sortByCostDescendingReOrdersList() {
            assertEquals(6, populatedVehicles.numberOfVehicles());
            //checks the order of the objects in the list
            assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(0));
            assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(1));
            assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(2));
            assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(3));
            assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(4));
            assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));

            populatedVehicles.sortByCostDescending();
            assertEquals(carbonFuelAboveBoundary, populatedVehicles.getVehicleByIndex(0));
            assertEquals(scooterAboveBoundary, populatedVehicles.getVehicleByIndex(1));
            assertEquals(scooterBelowBoundary, populatedVehicles.getVehicleByIndex(2));
            assertEquals(electricCarOnBoundary, populatedVehicles.getVehicleByIndex(3));
            assertEquals(electricCarBelowBoundary, populatedVehicles.getVehicleByIndex(4));
            assertEquals(carbonFuelOnBoundary, populatedVehicles.getVehicleByIndex(5));
        }

        @Test
        void sortByCostDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyVehicles.numberOfVehicles());
            emptyVehicles.sortByCostDescending();
        }
    }

}



