package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarbonFuelCarTest {

    CarbonFuelCar carValidAtBottomEdge, carInValidBelow, carValidAtTopEdge, carValidOverTopEdge;

    @BeforeEach
    void setup() {
        carValidAtBottomEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0, "diesel", 5, 1, 800, true);
        carInValidBelow = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0, "diesel", 4, 0, 799, true);
        carValidAtTopEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0, "petrol", 20, 5, 2500, false);
        carValidOverTopEdge = new CarbonFuelCar("notTesting", "NotTesting", 0, null, 0, 0, 0, 0, 0, "petrol", 21, 0, 2501, false);
    }

    @Nested
    class constructorTests {

        @Test
        void validatingFuelConsumption() {
            assertEquals(5, carValidAtBottomEdge.getFuelConsumption());
            assertEquals(5, carInValidBelow.getFuelConsumption());
            assertEquals(20, carValidAtTopEdge.getFuelConsumption());
            assertEquals(5, carValidOverTopEdge.getFuelConsumption());
        }

        @Test
        void validatingCarbonEmission() {
            assertEquals(1, carValidAtBottomEdge.getCarbonEmission());
            assertEquals(1, carInValidBelow.getCarbonEmission());
            assertEquals(5, carValidAtTopEdge.getCarbonEmission());
            assertEquals(1, carValidOverTopEdge.getCarbonEmission());
        }

        @Test
        void validatingEngineSize() {
            assertEquals(800, carValidAtBottomEdge.getEngineSize());
            assertEquals(800, carInValidBelow.getEngineSize());
            assertEquals(2500, carValidAtTopEdge.getEngineSize());
            assertEquals(800, carValidOverTopEdge.getEngineSize());
        }

    }


    @Nested
    class setterAndGettersTests {

        @Test
        void setGetFuelConsumption() {
            //100->120
            assertEquals(5, carValidAtBottomEdge.getFuelConsumption());
            carValidAtBottomEdge.setFuelConsumption(4);
            assertEquals(5, carValidAtBottomEdge.getFuelConsumption());
            carValidAtBottomEdge.setFuelConsumption(6);
            assertEquals(6, carValidAtBottomEdge.getFuelConsumption());
            carValidAtBottomEdge.setFuelConsumption(19);
            assertEquals(19, carValidAtBottomEdge.getFuelConsumption());
            carValidAtBottomEdge.setFuelConsumption(20);
            assertEquals(20, carValidAtBottomEdge.getFuelConsumption());
            carValidAtBottomEdge.setFuelConsumption(21);
            assertEquals(20, carValidAtBottomEdge.getFuelConsumption());
        }

        @Test
        void setGetCarbonEmission() {
            //100->120
            assertEquals(1, carValidAtBottomEdge.getCarbonEmission());
            carValidAtBottomEdge.setFuelConsumption(-1);
            assertEquals(1, carValidAtBottomEdge.getCarbonEmission());
            carValidAtBottomEdge.setFuelConsumption(1);
            assertEquals(1, carValidAtBottomEdge.getCarbonEmission());
        }

        @Test
        void setGetEngineSize() {
            //100->120
            assertEquals(800, carValidAtBottomEdge.getEngineSize());
            carValidAtBottomEdge.setEngineSize(799);
            assertEquals(800, carValidAtBottomEdge.getEngineSize());
            carValidAtBottomEdge.setEngineSize(801);
            assertEquals(801, carValidAtBottomEdge.getEngineSize());
            carValidAtBottomEdge.setEngineSize(2499);
            assertEquals(2499, carValidAtBottomEdge.getEngineSize());
            carValidAtBottomEdge.setEngineSize(2500);
            assertEquals(2500, carValidAtBottomEdge.getEngineSize());
            carValidAtBottomEdge.setEngineSize(2501);
            assertEquals(2500, carValidAtBottomEdge.getEngineSize());
        }
    }

    @Test
    void testEquals() {
        CarbonFuelCar car1 = new CarbonFuelCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 120, 4, 50, 100, "petrol", 5, 1, 800, false);
        CarbonFuelCar car2 = new CarbonFuelCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 120, 4, 50, 100, "petrol", 5, 1, 800, false);

        assertEquals(car1, car2);

        car2.setFuelConsumption(10);
        assertNotEquals(car1, car2);
        car2.setFuelConsumption(5);
        assertEquals(car1, car2);

        car2.setFuelType("diesel");
        assertNotEquals(car1, car2);
        car2.setFuelType("petrol");
        assertEquals(car1, car2);

        car2.setCarbonEmission(10);
        assertNotEquals(car1, car2);
        car2.setCarbonEmission(1);
        assertEquals(car1, car2);

        car2.setEngineSize(1230);
        assertNotEquals(car1, car2);
        car2.setEngineSize(800);
        assertEquals(car1, car2);

        car2.setAutomatic(true);
        assertNotEquals(car1, car2);
        car2.setAutomatic(false);
        assertEquals(car1, car2);
    }

    @Test
    void testToString() {
        Scooter scooter = new Scooter("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 2023, 250, 10, 110);
        // test that the inherited fields appear in the toString
        String strToTest = scooter.toString();
        assertTrue(strToTest.contains("ABCD5678"));  // reg number
        assertTrue(strToTest.contains("toyota789012345")); // make
        assertTrue(strToTest.contains("Toyota"));  //  manufacturer (name)
        assertTrue(strToTest.contains("1000")); //cost
        // now check the fields of Scooter subclass
        assertTrue(strToTest.contains("250")); //power
        assertTrue(strToTest.contains("Weight : 10")); //weight
        assertTrue(strToTest.contains("110")); //topRiderWeight


    }
}