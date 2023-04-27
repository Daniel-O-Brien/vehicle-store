package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElectricCarTest {
    ElectricCar carValidAtBottomEdge, carInValidBelow, carValidAtTopEdge, carValidOverTopEdge;

        @BeforeEach
        void setup() {
            carValidAtBottomEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 2021, 120, 4, 50, 100, 40, 100);
            carInValidBelow = new ElectricCar("notTesting", "NotTesting", 0, null, 2021, 119, 3, 49, 99, 39, 99);
            carValidAtTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 2021, 300, 25, 3000, 400, 60, 500);
            carValidOverTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 2021, 301, 26, 3001, 401, 61, 501);
        }

        @Nested
        class constructorTests {

            @Test
            void validatingEngineKWatts() {
                assertEquals(40, carValidAtBottomEdge.getEngineKWatts());
                assertEquals(40, carInValidBelow.getEngineKWatts());
                assertEquals(60, carValidAtTopEdge.getEngineKWatts());
                assertEquals(40, carValidOverTopEdge.getEngineKWatts());
            }

            @Test
            void validatingRange() {
                assertEquals(100, carValidAtBottomEdge.getRange());
                assertEquals(100, carInValidBelow.getRange());
                assertEquals(500, carValidAtTopEdge.getRange());
                assertEquals(100, carValidOverTopEdge.getRange());
            }


        }


        @Nested
        class setterAndGettersTests {

            @Test
            void setGetEngineKWatts() {
                //100->120
                assertEquals(40, carValidAtBottomEdge.getEngineKWatts());
                carValidAtBottomEdge.setEngineKWatts(39);
                assertEquals(40, carValidAtBottomEdge.getEngineKWatts());
                carValidAtBottomEdge.setEngineKWatts(41);
                assertEquals(41, carValidAtBottomEdge.getEngineKWatts());
                carValidAtBottomEdge.setEngineKWatts(59);
                assertEquals(59, carValidAtBottomEdge.getEngineKWatts());
                carValidAtBottomEdge.setEngineKWatts(60);
                assertEquals(60, carValidAtBottomEdge.getEngineKWatts());
                carValidAtBottomEdge.setEngineKWatts(61);
                assertEquals(60, carValidAtBottomEdge.getEngineKWatts());
            }

            @Test
            void setGetRange() {
                //100->120
                assertEquals(100, carValidAtBottomEdge.getRange());
                carValidAtBottomEdge.setRange(99);
                assertEquals(100, carValidAtBottomEdge.getRange());
                carValidAtBottomEdge.setRange(101);
                assertEquals(101, carValidAtBottomEdge.getRange());
                carValidAtBottomEdge.setRange(499);
                assertEquals(499, carValidAtBottomEdge.getRange());
                carValidAtBottomEdge.setRange(500);
                assertEquals(500, carValidAtBottomEdge.getRange());
                carValidAtBottomEdge.setRange(501);
                assertEquals(500, carValidAtBottomEdge.getRange());
            }
        }

        @Test
        void testEquals() {
            ElectricCar car1 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 0, 120, 4, 50, 100, 40, 100);
            ElectricCar car2 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota", 1000), 0, 120, 4, 50, 100, 40, 100);

            assertEquals(car1, car2);

            car2.setEngineKWatts(55);
            assertNotEquals(car1, car2);
            car2.setEngineKWatts(40);
            assertEquals(car1, car2);

            car2.setRange(300);
            assertNotEquals(car1, car2);
            car2.setRange(100);
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
