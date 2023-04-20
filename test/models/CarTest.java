package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    ElectricCar carValidAtBottomEdge, carInValidBelow, carValidAtTopEdge, carValidOverTopEdge;

        @BeforeEach
        void setup() {
            carValidAtBottomEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 120, 4, 50, 100, 0, 0);
            carInValidBelow = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 119, 3, 49, 99, 0, 0);
            carValidAtTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 300, 25, 3000, 400, 0, 0);
            carValidOverTopEdge = new ElectricCar("notTesting", "NotTesting", 0, null, 0, 301, 26, 3001, 401, 0, 0);
        }

        @Nested
        class constructorTests {
            @Test
            void validatingSecs0To60() {
                assertEquals(4, carValidAtBottomEdge.getSecs0To60());
                assertEquals(4, carInValidBelow.getSecs0To60());
                assertEquals(25, carValidAtTopEdge.getSecs0To60());
                assertEquals(4, carValidOverTopEdge.getSecs0To60());
            }

            @Test
            void validatingPower() {
                assertEquals(120, carValidAtBottomEdge.getPower());
                assertEquals(120, carInValidBelow.getPower());
                assertEquals(300, carValidAtTopEdge.getPower());
                assertEquals(120, carValidOverTopEdge.getPower());
            }

            @Test
            void validatingTorque() {
                assertEquals(100, carValidAtBottomEdge.getTorque());
                assertEquals(100, carInValidBelow.getTorque());
                assertEquals(400, carValidAtTopEdge.getTorque());
                assertEquals(100, carValidOverTopEdge.getTorque());
            }

            @Test
            void validatingTopSpeed() {
                assertEquals(50, carValidAtBottomEdge.getTopSpeed());
                assertEquals(50, carInValidBelow.getTopSpeed());
                assertEquals(3000, carValidAtTopEdge.getTopSpeed());
                assertEquals(50, carValidOverTopEdge.getTopSpeed());
            }

        }


        @Nested
        class setterAndGettersTests {
            @Test
            void setGetSecs0To60() {
                assertEquals(4, carValidAtBottomEdge.getSecs0To60());
                carValidAtBottomEdge.setSecs0To60(3);
                assertEquals(4, carValidAtBottomEdge.getSecs0To60());
                carValidAtBottomEdge.setSecs0To60(5);
                assertEquals(5, carValidAtBottomEdge.getSecs0To60());
                carValidAtBottomEdge.setSecs0To60(24);
                assertEquals(24, carValidAtBottomEdge.getSecs0To60());
                carValidAtBottomEdge.setSecs0To60(25);
                assertEquals(25, carValidAtBottomEdge.getSecs0To60());
                carValidAtBottomEdge.setSecs0To60(26);
                assertEquals(25, carValidAtBottomEdge.getSecs0To60());

            }

            @Test
            void setGetPower() {
                assertEquals(120, carValidAtBottomEdge.getPower());
                carValidAtBottomEdge.setPower(119);
                assertEquals(120, carValidAtBottomEdge.getPower());
                carValidAtBottomEdge.setPower(121);
                assertEquals(121, carValidAtBottomEdge.getPower());
                carValidAtBottomEdge.setPower(299);
                assertEquals(299, carValidAtBottomEdge.getPower());
                carValidAtBottomEdge.setPower(300);
                assertEquals(300, carValidAtBottomEdge.getPower());
                carValidAtBottomEdge.setPower(301);
                assertEquals(300, carValidAtBottomEdge.getPower());

            }

            @Test
            void setGetTorque() {
                //5 -> 100
                assertEquals(100, carValidAtBottomEdge.getTorque());
                carValidAtBottomEdge.setTorque(99);
                assertEquals(100, carValidAtBottomEdge.getTorque());
                carValidAtBottomEdge.setTorque(101);
                assertEquals(101, carValidAtBottomEdge.getTorque());
                carValidAtBottomEdge.setTorque(399);
                assertEquals(399, carValidAtBottomEdge.getTorque());
                carValidAtBottomEdge.setTorque(400);   //just inside 5 -> 100  range - should  be updated.
                assertEquals(400, carValidAtBottomEdge.getTorque());
                carValidAtBottomEdge.setTorque(401);   //just outside 5 -> 100 range - should  not be updated.
                assertEquals(400, carValidAtBottomEdge.getTorque());

            }

            @Test
            void setGetTopSpeed() {
                //100->120
                assertEquals(50, carValidAtBottomEdge.getTopSpeed());
                carValidAtBottomEdge.setTopSpeed(49);
                assertEquals(50, carValidAtBottomEdge.getTopSpeed());
                carValidAtBottomEdge.setTopSpeed(51);
                assertEquals(51, carValidAtBottomEdge.getTopSpeed());
                carValidAtBottomEdge.setTopSpeed(299);
                assertEquals(299, carValidAtBottomEdge.getTopSpeed());
                carValidAtBottomEdge.setTopSpeed(3000);
                assertEquals(3000, carValidAtBottomEdge.getTopSpeed());
                carValidAtBottomEdge.setTopSpeed(3001);
                assertEquals(3000, carValidAtBottomEdge.getTopSpeed());

            }
        }

        @Test
        void testEquals() {
            ElectricCar car1 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota",1000), 2023, 120, 4, 50, 100, 0, 0);
            ElectricCar car2 = new ElectricCar("ABCD5678", "toyota789012345", 1000, new Manufacturer("Toyota",1000), 2023, 120, 4, 50, 100, 0, 0);

            assertEquals(car1, car2);

            // Test the subclasses' fields
            car2.setPower(250);  // change value of power
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setPower(120);  // reset
            assertEquals(car1, car2);

            car2.setSecs0To60(15);  // change value of weight
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setSecs0To60(4);  // reset
            assertEquals(car1, car2);

            car2.setTopSpeed(2002);  // change value of weight
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setTopSpeed(50);  // reset
            assertEquals(car1, car2);

            car2.setTorque(300);  // change value of power
            assertNotEquals(car1, car2);  // check that the equals picks up the difference
            car2.setTorque(100);  // reset
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
